package seedu.storemando.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.storemando.commons.core.LogsCenter;
import seedu.storemando.commons.exceptions.DataConversionException;
import seedu.storemando.commons.exceptions.IllegalValueException;
import seedu.storemando.commons.util.FileUtil;
import seedu.storemando.commons.util.JsonUtil;
import seedu.storemando.model.ReadOnlyStoreMando;

/**
 * A class to access StoreMando data stored as a json file on the hard disk.
 */
public class JsonStoreMandoStorage implements StoreMandoStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonStoreMandoStorage.class);

    private Path filePath;

    public JsonStoreMandoStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getStoreMandoFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyStoreMando> readStoreMando() throws DataConversionException {
        return readStoreMando(filePath);
    }

    /**
     * Similar to {@link #readStoreMando()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyStoreMando> readStoreMando(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableStoreMando> jsonAddressBook = JsonUtil.readJsonFile(
            filePath, JsonSerializableStoreMando.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveStoreMando(ReadOnlyStoreMando addressBook) throws IOException {
        saveStoreMando(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveStoreMando(ReadOnlyStoreMando)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveStoreMando(ReadOnlyStoreMando addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableStoreMando(addressBook), filePath);
    }

}
