package seedu.storemando.testutil;

import seedu.storemando.model.StoreMando;
import seedu.storemando.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 * {@code StoreMando ab = new StoreMandoBuilder().withPerson("John", "Doe").build();}
 */
public class StoreMandoBuilder {

    private StoreMando storeMando;

    public StoreMandoBuilder() {
        storeMando = new StoreMando();
    }

    public StoreMandoBuilder(StoreMando storeMando) {
        this.storeMando = storeMando;
    }

    /**
     * Adds a new {@code Person} to the {@code StoreMando} that we are building.
     */
    public StoreMandoBuilder withPerson(Person person) {
        storeMando.addPerson(person);
        return this;
    }

    public StoreMando build() {
        return storeMando;
    }
}
