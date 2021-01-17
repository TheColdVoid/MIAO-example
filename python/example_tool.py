# -*- coding: utf-8 -*
from miaolib import start, web_demo
import string
import random


@web_demo('随机选取', description="从输入的选项里面任选其一，选项以逗号分隔")
def random_choice(options: str):
    delim = ","
    if "，" in options:
        delim = "，"
    list = options.split(delim)
    return random.choice(list)



digs = string.digits + string.ascii_uppercase


@web_demo("十进制转任意进制", param_names=["要被转换的数", "要转换到的进制"])
def int2base(x: int, base: int):
    if x < 0:
        sign = -1
    elif x == 0:
        return digs[0]
    else:
        sign = 1

    x *= sign
    digits = []

    while x:
        digits.append(digs[int(x % base)])
        x = int(x / base)

    if sign < 0:
        digits.append('-')

    digits.reverse()

    return ''.join(digits)


# reference from https://stackoverflow.com/questions/2267362/how-to-convert-an-integer-to-a-string-in-any-base
start("使用场景示例：工具合集",2334)
