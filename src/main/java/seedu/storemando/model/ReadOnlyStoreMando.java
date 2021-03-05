package seedu.storemando.model;

import javafx.collections.ObservableList;
import seedu.storemando.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyStoreMando {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
