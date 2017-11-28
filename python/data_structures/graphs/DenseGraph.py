"""An implementation of a Graph. THe underlying data structure is an
adjacency matrix."""

# Standard Library
from functools import reduce
from itertools import chain
from pprint import pformat
from typing import Dict, Hashable, Iterable, List, Optional, Set, Tuple, TypeVar, SupportsInt

# 3rd Party
import numpy as np
from Graph import Graph

Label = TypeVar('Node', Hashable, Hashable)
PayLoad = TypeVar('PayLoad')
Edge = Tuple[Label, Label]
Edges = Iterable[Edge]
AdjecancyList = Iterable[Tuple[Label, Iterable[Label]]]


class DenseGraph(Graph):
    def __init__(self, adjecancy_list: AdjecancyList):

        #  from pudb import set_trace; set_trace()

        # map from Node to an integer for faster operations on the matrix
        # (which needs int indecies anyway)
        self.map: Dict[Label, SupportsInt] = dict()

        counter = 0

        for item in set(reduce(lambda x, y: chain(x, y), adjecancy_list)):
            self.map[item] = counter
            counter += 1

        self.matrix: np.ndarray = np.full(
                fill_value=False, shape=(self.order, self.order), dtype=np.bool_
        )

        for entry in adjecancy_list:
            self.connect(entry[0], entry[1])

    def connected(self, node_a: Label, node_b: Label) -> bool:
        return self.matrix[self._lookup(node_a), self._lookup(node_b)]

    @property
    def nodes(self) -> Iterable[Label]:
        return self.map.keys()

    @property
    def directed(self) -> True:
        return True

    def _lookup(self, key: Label) -> Optional[int]:
        return self.map.get(key)

    @property
    def order(self) -> int:
        return len(self.nodes)

    def __repr__(self) -> str:
        return pformat([f'{i[0]} -> {i[1]}' for i in self.edges])

    @property
    def edges(self) -> Iterable[Tuple[Label, Label]]:
        for node1 in self.nodes:
            i = self._lookup(node1)

            for node2 in self.nodes:
                j = self._lookup(node2)

                if self.matrix[i, j]:
                    yield node1, node2

    def path(self, node_a: Label, node_b: Label) -> List[Label]:
        """
        Dijkstra's algorithm.
        :param node_a:
        :param node_b:
        :return: list of Node Labels
        """
        pass

    def connect(self, node_a: Label, node_b: Label):
        """
        Update the adjacency matrix. Set the slot to True to represent the
        connection between node_a and node_b.
        :param node_a:
        :param node_b:
        :return:
        """
        self.matrix[self._lookup(node_a), self._lookup(node_b)] = True


# run only if run as script
if __name__ == "__main__":
    low: SupportsInt = 50
    high: SupportsInt = 9999
    x: DenseGraph = DenseGraph(np.random.randint(low, high, size=(high, 2)))
    print(x)
