package org.supercat.growstone.containers;

import com.supercat.growstone.network.messages.ZResource;
import com.supercat.growstone.network.messages.ZTier;

import java.util.Objects;

public class CollectionKey {
    public ZResource.Type type;
    public long collectionId;

    public CollectionKey(ZResource.Type type, long collectionId) {
        this.type = type;
        this.collectionId = collectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionKey key = (CollectionKey) o;
        return this.type == key.type  && this.collectionId == key.collectionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type, this.collectionId);
    }

}
