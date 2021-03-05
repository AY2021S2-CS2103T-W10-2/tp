package seedu.storemando.model;

import static java.util.Objects.requireNonNull;
import static seedu.storemando.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.storemando.commons.core.GuiSettings;
import seedu.storemando.commons.core.LogsCenter;
import seedu.storemando.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final StoreMando storeMando;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given storeMando and userPrefs.
     */
    public ModelManager(ReadOnlyStoreMando addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.storeMando = new StoreMando(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.storeMando.getPersonList());
    }

    public ModelManager() {
        this(new StoreMando(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getStoreMandoFilePath() {
        return userPrefs.getStoreMandoFilePath();
    }

    @Override
    public void setStoreMandoFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setStoreMandoFilePath(addressBookFilePath);
    }

    //=========== StoreMando ================================================================================

    @Override
    public void setStoreMando(ReadOnlyStoreMando addressBook) {
        this.storeMando.resetData(addressBook);
    }

    @Override
    public ReadOnlyStoreMando getStoreMando() {
        return storeMando;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return storeMando.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        storeMando.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        storeMando.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        storeMando.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return storeMando.equals(other.storeMando)
            && userPrefs.equals(other.userPrefs)
            && filteredPersons.equals(other.filteredPersons);
    }

}
