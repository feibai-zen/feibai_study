#!/opt/homebrew/bin/python3
# encoding=utf-8

def main():
  file = open("long_text_2020-09-25-15-17-38.txt", 'r')
  str = ""
  for line in file.readlines():
    str = str + " " + line.replace("\n", "")
  file.close()
  print(str)


if __name__ == '__main__':
  main()
