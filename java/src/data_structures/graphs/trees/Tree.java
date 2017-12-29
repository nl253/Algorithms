package data_structures.graphs.trees;

import java.util.Collection;
import java.util.Optional;

public interface Tree {

    Optional<Collection<Tree>> getDescendants();

    int getHeight();
}
