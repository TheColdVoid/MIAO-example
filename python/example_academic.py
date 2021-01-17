# -*- coding: utf-8 -*
import jieba
import jieba.posseg as pseg
from miaolib import web_demo,start

jieba.enable_paddle()
@web_demo("分词",description="基于jieba",visualization='table')
def word_split(text):
   words = pseg.cut(text,use_paddle=True)
   words = [{'index':index,'词语':w,'词性':f} for index,(w,f) in enumerate(words)]
   return words
start("使用场景示例：学术成果展示",2336)

