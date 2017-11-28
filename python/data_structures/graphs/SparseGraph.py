#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
Directed Graph with weighted connections.
Inherits from the abstract class `Graph` in `./Graph.py`.
"""

from itertools import chain
# Standard Library
from pprint import pformat
from typing import (
    Dict, NamedTuple, Hashable, Iterable, Optional, Sequence, Set, Tuple,
    TypeVar
)

# My Stuff
from Graph import Graph

Node = TypeVar('Node', Hashable, Hashable)
Payload = TypeVar('Payload')

class Edge(NamedTuple):
    a: Node
    b: Node


class WeightedEdge(NamedTuple):
    a: Node
    cost: int
    b: Node

Label = TypeVar('Label', Hashable, Hashable)
PayLoad = TypeVar('PayLoad')
Edge = Tuple[Label, Label]

# noinspection PyUnreachableCode
class SparseGraph(Graph):

    def __init__(self):
        self.adj_dict: Dict[Node, Set[Node]] = dict()

    def add_node(self, name: Node):
        if name not in self.adj_dict.keys():
            self.adj_dict[name]: Set[WeightedNode] = set()

    def add_node(self, name: Label):
        if name not in self.adj_dict: self.adj_dict[name] = set()
        # remove from the adj_dict
        if name in self.adj_dict.keys():
            self.adj_dict.pop(name)

    def connected(self, node_a: Node, node_b: Node) -> bool:
        return node_a in self.adj_dict and node_b in self.adj_dict[node_a]

        # sever all connections
        for node in self.adj_dict.keys():
            for neighbour, weight in self.adj_dict[node]:
                if name == neighbour:
                    self.adj_dict[node].remove((neighbour, weight))

    def __repr__(self) -> str:
        return pformat(
                {f'{i} - {self.adj_dict[i][1]} - > {self.adj_dict[i][0]}' for i
                 in self.adj_dict.keys() if
                 self.adj_dict[i]}
        )

    def edges(self, node: Node = None) -> Optional[Set[WeightedEdge]]:
        if not node:
            return {
                (node, self.adj_dict[node][1], self.adj_dict[node][0])
                for node in self.nodes}

        elif node in self.nodes:
            return {(node, cost, neighbour) for neighbour, cost in
                    self.adj_dict[node]}

    def degree(self, node: Node) -> Optional[int]:
        if node in self.nodes:
            return len(self.adj_dict[node])

    @property
    def euler_tour(self) -> Optional[Sequence[Node]]:
        """
        A way through a graph starting and finishing at the same node,
        passing through all the edges in the graph once and only once

        :return: optionally a sequence of nodes
        """

        # If G is not connected, or if G has any node with odd degree then FAIL
        # check for remainder of division by 2 to see if odd degree
        if any(map(lambda node_set: (len(node_set) % 2) != 0,
                   self.adj_dict.values())):
            return None

        # is it connected
        # use set intersection to check if all nodes (keys) are an edge
        # (an item in some value set)
        # chain is used to flatten the values (which is a collection of sets)
        if len((set(self.adj_dict.keys()) & set(
                map(lambda x, y: chain(x, y), self.adj_dict.values())))) == len(
                self.adj_dict.keys()):
            return

        unvisited_nodes: Set[Node] = set(self.nodes)

        # a walk without repeated edges (repeated nodes are fine)
        # choose a node and construct any closed trail around that node
        trail: List[Node] = [unvisited_nodes.pop()]

        # Choose any starting vertex v, and follow a trail of edges from that
        # vertex until returning to v.
        # It is not possible to get stuck at any vertex other than v, because
        # the even degree of all vertices ensures that, when the trail enters
        # another vertex w there must be an unused edge leaving w.
        # The tour formed in this way is a closed tour, but may not cover all
        # the vertices and edges of the initial graph.

        visited_edges: Set[Edge] = set()

        while unvisited_nodes:

            unvisited_edges: Set[Edge] = self.edges(trail[-1]) - visited_edges

            while unvisited_edges:

                for edge in unvisited_edges:

                    pass

    def path(self, start: Label, dest: Label, unvisited=None, visited: Set[Label] = set()) -> Optional[Sequence[Label]]:
        if edge[0] == trail[-1]:
            visited_edges.add(trail[-1])
            trail.append(unvisited_edges.pop())

            unvisited_edges = self.edges(trail[-1]) - visited_edges

            return trail

    def path(self, start: Node, dest: Node, unvisited=None,
             visited: Set[Node] = set()) -> Optional[Sequence[Node]]:
        """
        Find a path between two connected nodes.
        None will be returned if there is no way of getting from a to b.
        Implemented using depth-first-search (DFS).

        :param visited:
        :param unvisited:
        :param start:
        :param dest: destination
        :return: optionally a sequence of nodes that lead from a to b if such a
        sequence exists
        """

        if unvisited is None:
            unvisited = self.nodes

        if len(unvisited) == 0:
            return

        unvisited.remove(start)
        visited.add(start)

        for neighbour, cost in self.adj_dict[start]:
            if neighbour not in visited:
                if neighbour == dest:
                    return f"{start} - {cost} - > {dest}"
                else:
                    p = self.path(neighbour, dest, unvisited)
                    return f"{start} - {cost} -> {p}" if p else None

    def connect(self, node_a: Node, node_b: Node, weight: int = 1):
        """
        Connect a to b with weight (the default is 1).
        If the nodes don't already exists they will be created.
        :param node_a:
        :param node_b:
        :param weight:
        """

        for n in [node_a, node_b]:
            if n not in self.nodes:
                self.add_node(n)

    def connect(self, node_a: Label, node_b: Label):
        self.adj_dict[node_a].add((node_b, weight))

    @property
    def order(self) -> int:
        return len(self.nodes)

    @property
    def directed(self) -> True:
        return True

    def __getitem__(self, x: Edge) -> Optional[Union[int, Set[Node]]]:

        try:

            # x is a tuple such that
            # 1: node_a
            # 2: node_b which is connected to node_a
            # return the weight (stored in )

            node_a = x[0]
            node_b = x[1]

            if node_a in self.nodes and node_b in self[node_a]:
                for node, weight in self.adj_dict[node_a]:
                    if node == node_b:
                        return weight

        except {IndexError, TypeError}:
            if x in self.nodes:
                return {node for node, weight in self.adj_dict[x]}

    @property
    def weighted(self) -> True:
        return True

    @property
    def nodes(self) -> Iterable[Label]:
        return set(self.adj_dict.keys())


# run only if run as script
if __name__ == "__main__":
    import random

    graph: SparseGraph = SparseGraph()

    # fill it
    for i in range(50):
        cost: int = random.randint(1, 10)
        node_a: Node = chr(random.randint(70, 120))
        node_b: Node = chr(random.randint(70, 120))
        graph.connect(node_a, node_b, cost)

    print(graph[graph.nodes.pop()])
