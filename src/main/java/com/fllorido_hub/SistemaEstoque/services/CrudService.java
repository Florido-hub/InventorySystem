package com.fllorido_hub.SistemaEstoque.services;

import java.util.List;

public interface CrudService <Entity, Long>{
    List<Entity> findAll();

    /**
     * Retrieves an entity by its unique identifier.
     *
     * @param id The unique identifier of the entity to retrieve.
     * @return The entity with the specified ID.
     */
    Entity findById(Long id);

    /**
     * Creates a new entity.
     *
     * @param entity The entity to be created.
     * @return The newly created entity.
     */
    Entity create(Entity entity);

    /**
     * Updates an existing entity.
     *
     * @param id The unique identifier of the entity to update.
     * @param newEntity The updated entity data.
     * @return The updated entity.
     */
    Entity update(Long id, Entity newEntity);

    /**
     * Deletes an entity by its unique identifier.
     *
     * @param id The unique identifier of the entity to delete.
     */
    void delete(Long id);

}
