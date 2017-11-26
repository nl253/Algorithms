#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Standard Library
from collections import deque
from typing import Iterable

# 3rd Party
from tokenizer import Token


def handle(token: Token):
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
