package seedu.storemando.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.storemando.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.storemando.commons.exceptions.IllegalValueException;
import seedu.storemando.commons.util.JsonUtil;
import seedu.storemando.model.StoreMando;
import seedu.storemando.testutil.TypicalPersons;

public class JsonSerializableStoreMandoTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableStoreMandoTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsStoreMando.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonStoreMando.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonStoreMando.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableStoreMando dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
            JsonSerializableStoreMando.class).get();
        StoreMando storeMandoFromFile = dataFromFile.toModelType();
        StoreMando typicalPersonsStoreMando = TypicalPersons.getTypicalStoreMando();
        assertEquals(storeMandoFromFile, typicalPersonsStoreMando);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableStoreMando dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
            JsonSerializableStoreMando.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableStoreMando dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
            JsonSerializableStoreMando.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableStoreMando.MESSAGE_DUPLICATE_PERSON,
            dataFromFile::toModelType);
    }

}
