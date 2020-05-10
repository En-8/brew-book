package dev.innate.model;

/**
 * This is the model class that represents the payload of a delete request when a user wishes to delete a brew.
 */
public class DeleteBrewRequest {
    private int id;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }
}
