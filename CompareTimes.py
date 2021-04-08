import matplotlib.pyplot as plt
import math

ms_times = []
bs_times = []
o_n_logn = []
o_n2 = []
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
        bs_f = open("output/bs_times.txt", "r")
        line = bs_f.readline()
        while (line != ""):
            bs_times.append(float(line.replace("\n", "")))
            line = bs_f.readline()
    except:
        print("An error occured with bs_times")
    finally:
        bs_f.close()

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

    fig = plt.figure(figsize=(8, 8), num="Comparison")
    fig.tight_layout() 
    
    ax1 = fig.add_subplot(2, 2, 1)
    ax1.title.set_text("Bubble sort")
    ax1.set_xlabel("Size of list")
    ax1.set_ylabel("Time (ms)")
    ax1.scatter(list_sizes, bs_times, color='red', label="Bubble Sort", s=1)

    ax2 = fig.add_subplot(2, 2, 2)
    ax2.title.set_text("Merge sort")
    ax2.set_xlabel("Size of list")
    ax2.set_ylabel("Time (ms)")
    ax2.scatter(list_sizes, ms_times, color='blue', label="Merge Sort", s=1)

    ax3 = fig.add_subplot(2, 2, 3)
    ax3.title.set_text("O(n^2)")
    ax3.set_xlabel("Size of list")
    ax3.set_ylabel("Time")
    ax3.set_yticks([])
    ax3.scatter(list_sizes, o_n2, color='green', label="n^2", s=1)

    ax4 = fig.add_subplot(2, 2, 4)
    ax4.title.set_text("O(nlog(n))")
    ax4.set_xlabel("Size of list")
    ax4.set_ylabel("Time")
    ax4.set_yticks([])
    ax4.scatter(list_sizes, o_n_logn, color='pink', label="nlog(n)", s=1)

    plt.subplots_adjust(left=0.2,
                        bottom=0.1, 
                        right=0.9, 
                        top=0.9, 
                        wspace=0.4, 
                        hspace=0.4)
    plt.show()
    plt.savefig('comparison.png')

def generate_sets():
    for size in list_sizes:
        o_n_logn.append(size * math.log(size))
        o_n2.append(size**2)

if __name__ == "__main__":
    print("Generating graphs")
    read_in()
    generate_sets()
    display()