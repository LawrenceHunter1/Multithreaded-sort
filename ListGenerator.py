import random, sys, argparse

if __name__ == '__main__':

    parser = argparse.ArgumentParser()
    parser.add_argument('-n', '--items', default=1000, help="Number of items in generated list")
    parser.add_argument('-r', '--range', default=100, help="Upper bound of number generator")
    args = parser.parse_args()
    n = int(args.items)
    r = int(args.range)

    f = open("list_in.txt", "w+")

    for i in range(n):
        f.write(str(random.randint(0, r)))
        f.write("\n")
