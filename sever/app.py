from fastapi import FastAPI, WebSocket
from fastapi.responses import JSONResponse
from fastapi.staticfiles import StaticFiles
from pydantic import BaseModel
from typing import List, Optional
from sklearn.externals import joblib
from keybert import KeyBERT
from gradio_client import Client
import json
import uvicorn
import asyncio

# Initialize FastAPI application
app = FastAPI()

# Load pre-trained models
clf = joblib.load("C:/Users/82104/IdeaProjects/suver/kw_model.pkl")
keybert_model = KeyBERT()

# Initialize Gradio client
client = Client("ashawkey/LGM", hf_token="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")

class Message(BaseModel):
    role: str
    content: str

# FastAPI route for text classification using the loaded model
@app.post("/model")
async def classify_text(text: str):
    predict = clf.predict([text])
    result = predict[0]
    return JSONResponse(content={"result": result})

# FastAPI route for keyword extraction using KeyBERT
@app.post("/keywords")
async def extract_keywords(user_prompt: str):
    keywords = keybert_model.extract_keywords(user_prompt, top_n=5)
    return JSONResponse(content={"keywords": keywords})

# FastAPI route for Gradio API call
@app.post("/generate")
async def generate_image(word: str):
    # Call the Gradio API with specified parameters
    result = client.predict(
        None,   # Filepath, None if no image is uploaded
        word,   # Prompt for the generation
        "ugly", # Negative prompt
        0,      # Elevation angle
        50,     # Inference steps
        42,     # Random seed
        api_name="/process"
    )
    return JSONResponse(content={"result": result})

# FastAPI WebSocket endpoint for real-time keyword extraction
@app.websocket("/ws")
async def websocket_endpoint(websocket: WebSocket):
    await websocket.accept()
    try:
        data = json.loads(await websocket.receive_text())
        user_prompt = data.get("user_prompt", "")
        keywords = keybert_model.extract_keywords(user_prompt, top_n=5)

        # Stream keywords to WebSocket
        for keyword, _ in keywords:
            await websocket.send_text(keyword)
            await asyncio.sleep(0.1)  # Delay between keywords

        await websocket.send_text("<EOS>")  # End of stream
    finally:
        await websocket.close()

# Serve static files
app.mount("/", StaticFiles(directory="../../res/static", html=True), name="static")

# Run the FastAPI application
if __name__ == "__main__":
    uvicorn.run(app, host="127.0.0.1", port=8000)

