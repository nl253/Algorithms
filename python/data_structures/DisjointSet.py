#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Standard Library
import os, sys, logging
from logging import Logger

# initalise logging with sane configuration
logging.basicConfig(
    level=logging.DEBUG, format='%(levelname)s:%(asctime)s  %(message)s'
)

# declare a logger
log: Logger = logging.getLogger()

class Forest:

    def union(self):
        print()

    def find(self):
        """Find the connected component the vertex (node) belongs to.
        Two nodes are connected iff they share a parent.
        """
        pass

    def __repr__(self):
        return f"sdlfkj {1}i"


# run only if run as script
if __name__ == "__main__":
    forest = Forest()
    print()
