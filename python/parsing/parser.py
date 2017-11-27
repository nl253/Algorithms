#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Standard Library
from collections import deque
from typing import Iterable

# 3rd Party
from .tokenizer import Token

import numpy as np

"""
parse table contains numbers the correspond to rules
None - error
0 - no further action can be taken
1 - apply rule 1
2 - apply rule 2
...


States
------

push to output ::= (TRUE | FALSE | VAR) atom 

await next to output ::= TOKEN q


---------------------------
|
|
|
|
|
|
|
|
"""
np.array([
    [],
    [],
    [],
    []
])


def rule1():
    pass


def rule2():
    pass


def rule3():
    pass


def rule4():
    pass


def parse(input_tokens: Iterable[Token]) -> Iterable[Token]:
    if not input_tokens: raise Exception()
    output: deque = deque(input_tokens)
    output.append(Token("EOF", None))  # marks end of input

    while True:
        handle(output)
        next_ = next(output)

        if focus.type in TERMINALS:
            continue

        elif focus.type == "LPAREN":
            output.append(focus)

        else:
            return focus

        focus = output.popleft()

    return output
