

# Standard Library
from abc import ABCMeta, abstractmethod
from typing import Any, Hashable, Iterable, Optional, TypeVar

Label = TypeVar('Node', Hashable, Hashable)
PayLoad = TypeVar('PayLoad')


class Graph(metaclass=ABCMeta):
    __metaclass__ = ABCMeta

    @property
    @abstractmethod
    def nodes(self) -> Iterable:
        """
        The nodes stored in this Graph.

        :return: Iterable of Nodes, the representation will vary depending on
        the implementation.
        """
        pass

    @property
    @abstractmethod
    def edges(self) -> Iterable:
        """
        The nodes stored in this Graph.

        :return: a collection of Edges where each edge is a Pair representing
        two connected Nodes.
        """
        pass

    @property
    @abstractmethod
    def order(self) -> int:
        """
        Order of the Graph ie the number of Edges.
        :return: the number of edges
        """
        pass

    @property
    @abstractmethod
    def directed(self) -> bool:
        """
        Boolean value that tells you whether the graph is directed or undirected.
        :return: boolean
        """
        pass

    @abstractmethod
    def connect(self, nodeA: Label, nodeB: Label):
        """
        Add a new edge ie connect two edges. The nodes MUST exist.

        :param nodeA:
        :param nodeB:
        :return: nothing ie None
        """
        pass

    @abstractmethod
    def connected(self, nodeA: Label, nodeB: Label) -> bool:
        """
        :return: True if A and B are connected, otherwise False.
        """
        pass

    @abstractmethod
    def path(self, nodeA: Label, nodeB: Label) -> Any:
        """
        Find the path from a to b. Note that a and b MUST BE in the graph.
        Best to perform a search on the nodes property.
        :param nodeA:
        :param nodeB:
        :return: a pair with the firs element being the cost and the second
        the path
        """
        pass
