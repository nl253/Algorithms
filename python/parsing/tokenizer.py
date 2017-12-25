#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Standard Library
from collections import namedtuple
from typing import *

Token = namedtuple('Tuple', ['type', 'data'])

IFF = r"(?P<IFF>iff|(is )?equiv(alent)?( to)?)"
DISJ = r"(?P<DISJ>or|v|\|{1,2})"
CONJ = r"(?P<CONJ>and|&{1,2})"
IMPL = r"(?P<IMPL>->|implies)"
XOR = r"(?P<XOR>(exclusive |x)or)"
ENT = r"(?P<ENT>entails?)"
BOP = r"(?P<BOP>{0})".format("|".join([ENT, IFF, IMPL, XOR, DISJ, CONJ]))
NEG = r"(?P<NEG>not|(negation|falsehood) of)"
VAR = r"(?P<VAR>[P-Z])"
LPAREN = r"(?P<LPAREN>\()"
RPAREN = r"(?P<RPAREN>\))"
COMMA = r"(?P<COMMA>,)"
NL = r"(?P<NL>\n)"
TRUE = r"(?P<TRUE>true)"
FALSE = r"(?P<FALSE>false)"


def tokenize(input: str) -> Iterable[Token]:
    import re
    pat = re.compile(
            r"|".join([NEG, BOP, TRUE, FALSE, VAR, NL, LPAREN, RPAREN, COMMA]),
            flags=re.MULTILINE
    )
    for match in pat.finditer(input.strip()):
        matches = match.groupdict()
        yield Token([k for k in matches.keys() if matches[k]], match.group(0))


if __name__ == "__main__":
    import sys, os

    f = sys.argv[1] \
        if len(sys.argv) > 1 \
        else os.path.join(os.path.dirname(__file__), 'file.txt')

    with open(f) as f:
        for line in f.read().strip().splitlines():
            for token in tokenize(line):
                print(token)
