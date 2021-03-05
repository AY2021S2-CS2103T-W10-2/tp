package seedu.storemando.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.storemando.commons.exceptions.IllegalValueException;
import seedu.storemando.model.ReadOnlyStoreMando;
import seedu.storemando.model.StoreMando;
import seedu.storemando.model.person.Person;

/**
 * An Immutable StoreMando that is serializable to JSON format.
 */
@JsonRootName(value = "storemando")
class JsonSerializableStoreMando {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableStoreMando} with the given persons.
     */
    @JsonCreator
    public JsonSerializableStoreMando(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyStoreMando} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableStoreMando}.
     */
    public JsonSerializableStoreMando(ReadOnlyStoreMando source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code StoreMando} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public StoreMando toModelType() throws IllegalValueException {
        StoreMando storeMando = new StoreMando();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (storeMando.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            storeMando.addPerson(person);
        }
        return storeMando;
    }

}
