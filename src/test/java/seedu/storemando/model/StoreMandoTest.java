package seedu.storemando.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.storemando.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.storemando.testutil.Assert.assertThrows;
import static seedu.storemando.testutil.TypicalPersons.ALICE;
import static seedu.storemando.testutil.TypicalPersons.getTypicalStoreMando;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.storemando.model.person.Person;
import seedu.storemando.model.person.exceptions.DuplicatePersonException;
import seedu.storemando.testutil.PersonBuilder;

public class StoreMandoTest {

    private final StoreMando storeMando = new StoreMando();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), storeMando.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> storeMando.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyStoreMando_replacesData() {
        StoreMando newData = getTypicalStoreMando();
        storeMando.resetData(newData);
        assertEquals(newData, storeMando);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        StoreMandoStub newData = new StoreMandoStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> storeMando.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> storeMando.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInStoreMando_returnsFalse() {
        assertFalse(storeMando.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInStoreMando_returnsTrue() {
        storeMando.addPerson(ALICE);
        assertTrue(storeMando.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInStoreMando_returnsTrue() {
        storeMando.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        assertTrue(storeMando.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> storeMando.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyStoreMando whose persons list can violate interface constraints.
     */
    private static class StoreMandoStub implements ReadOnlyStoreMando {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        StoreMandoStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
