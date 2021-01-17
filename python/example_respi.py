# -*- coding: utf-8 -*
from miaolib import web_demo, start


# 此例子仅用来演示MIAO的使用场景，因此将不真正操作树莓派。
# (注释掉的部分代码可用于实际使用)
# import RPi.GPIO as GPIO
# relay = 18
# GPIO.setmode(GPIO.BOARD)
# GPIO.setup(relay, GPIO.OUT)
@web_demo("开灯")
def turn_on_light():
    # GPIO.output(relay, GPIO.HIGH)
    return "成功"


@web_demo("关灯")
def turn_on_light():
    # GPIO.output(relay, GPIO.LOW)
    return "成功"


@web_demo("查看树莓派温度")
def get_temp():
    # with open("/sys/class/thermal/thermal_zone0/temp","r") as f:
    #     temp = int(f.read())
    #     return str(temp/1000)+"°"
    return "42.33摄氏度"


start("使用场景示例：物联网",2335)
