import random

f = open("list_in.txt", "w+")

for i in range(1000):
    f.write(str(random.randint(0, 100)))
    f.write("\n")
