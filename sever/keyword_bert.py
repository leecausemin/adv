#키워드 추출
from keybert import KeyBERT


model = KeyBERT()  # KeyBERT 모델 초기화

text = """
Today, my mom bought me a skateboard as a birthday present. I was so excited that I went out to ride it right away. It was so much fun that I think I’ll ride it often.
"""

# 키워드 추출
keywords = model.extract_keywords(text, keyphrase_ngram_range=(1, 1), stop_words='english', top_n=1)

# 결과 출력
for word, score in keywords:
    print(f"Keyword: {word}, Score: {score:.4f}")

import pickle

# 모델 가정: model
with open("kw_model.pkl", "wb") as f:
    pickle.dump(model, f)
