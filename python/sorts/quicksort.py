#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
author: nl253
date:   Thu 23 Nov 21:55:32 GMT 2017
python: Python 3.6.3

Note that because this is a recursive and very inefficient
implementation you can sort at most around 2.000 elements.
"""


def quicksort(array):
    return array if (not array) or (len(array) <= 1) else quicksort([
        i for i in array[1:] if i < array[0]
    ]) + [array[0]] + quicksort([i for i in array[1:] if i >= array[0]])


# run only if run as script
if __name__ == "__main__":
    from pprint import pprint
    pprint(quicksort(range(1000, 0, -1)))
