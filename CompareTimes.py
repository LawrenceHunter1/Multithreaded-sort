import matplotlib.pyplot as plt

ms_times = []
ls_times = []
list_sizes = []

def read_in():
    try:
        ms_f = open("output/ms_times.txt", "r")
        line = ms_f.readline()
        while (line != ""):
            ms_times.append(float(line.replace("\n", "")))
            line = ms_f.readline()
    except:
        print("An error occured with ms_times")
    finally:
        ms_f.close()

    try:
        ls_f = open("output/ls_times.txt", "r")
        line = ls_f.readline()
        while (line != ""):
            ls_times.append(float(line.replace("\n", "")))
            line = ls_f.readline()
    except:
        print("An error occured with ls_times")
    finally:
        ls_f.close()

    try:
        s_f = open("output/l_sizes.txt", "r")
        line = s_f.readline()
        while (line != ""):
            list_sizes.append(int(line.replace("\n", "")))
            line = s_f.readline()
    except:
        print("An error occured with list_sizes")
    finally:
        s_f.close()

def display():
    plt.title("Time to sort")
    plt.xlabel("Size of list")
    plt.ylabel("Time (ms)")
    plt.scatter(ms_times, list_sizes, color='blue', label="Merge Sort")
    plt.scatter(ls_times, list_sizes, color='red', label="Linear Sort")
    plt.legend()
    plt.show()


if __name__ == "__main__":
    read_in()
    display()