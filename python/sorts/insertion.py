#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
AUTHOR: $AUTHORS
DATE: $DATE at $TIME
PYTHON: $PYTHON_VERSION PYTHON_IMPLEMENTATION $PYTHON_COMPILER
"""

# Standard Library
import os, sys
import logging
from logging import Logger

# import types for static typing (mypy, pycharm etc)
from typing import List, Iterator, Iterable, SupportsInt


def insertion_sort(x: List[SupportsInt]):
    for i in range(len(x)):
        for j in range(len(x) - 1, i - 1, -1):
            if x[i] >= x[j]:
                x.insert(i, x.pop(j))
    return x


# run only if run as script
if __name__ == "__main__":
    from pprint import pprint
    pprint(insertion_sort(list(range(10, 0, -1))))
