#!#!/usr/local/bin/python3
# encoding=utf-8

def main():
  file = open("./total.txt", 'r')
  file_400 = open("./400.txt", 'w')
  file_609 = open("./609.txt", 'w')
  file_601 = open("./601.txt", 'w')

  for line in file.readlines():
    if line.__contains__('"businessTypeId":400'):
      file_400.write(line)

    if line.__contains__('"businessTypeId":609'):
      file_609.write(line)

    if line.__contains__('"businessTypeId":601'):
      file_601.write(line)

  file_400.close()
  file_601.close()
  file_609.close()


if __name__ == '__main__':
  main()
