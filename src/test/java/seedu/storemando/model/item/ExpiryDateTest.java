package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExpiryDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExpiryDate(null));
    }

    @Test
    public void constructor_invalidExpiryDate_throwsIllegalArgumentException() {
        String invalidExpiryDate = "";
        assertThrows(IllegalArgumentException.class, () -> new ExpiryDate(invalidExpiryDate));
    }

    @Test
    public void isValidExpiryDate() {
        // null expirydate
        assertThrows(NullPointerException.class, () -> ExpiryDate.isValidExpiryDate(null));

        // blank expirydate
        assertFalse(ExpiryDate.isValidExpiryDate("")); // empty string
        assertFalse(ExpiryDate.isValidExpiryDate(" ")); // spaces only

        // missing parts
        assertFalse(ExpiryDate.isValidExpiryDate("2020-10")); // missing DD
        assertFalse(ExpiryDate.isValidExpiryDate("20201010")); // missing '-' symbol
        assertFalse(ExpiryDate.isValidExpiryDate("10-10")); // missing YYYY
        assertFalse(ExpiryDate.isValidExpiryDate("2020-1-10")); // missing digit for MM
        assertFalse(ExpiryDate.isValidExpiryDate("20-11-01")); // missing digit for YYYY
        assertFalse(ExpiryDate.isValidExpiryDate("2020-11-1")); // missing digit for DD

        // invalid parts
        assertFalse(ExpiryDate.isValidExpiryDate("2010-10-40")); // invalid DD
        assertFalse(ExpiryDate.isValidExpiryDate("2010-20-01")); // invalid MM
        assertFalse(ExpiryDate.isValidExpiryDate("2020-1 0-11")); // spaces in DD
        assertFalse(ExpiryDate.isValidExpiryDate("20 20-10-11")); // spaces in YYYY
        assertFalse(ExpiryDate.isValidExpiryDate(" 2020-10-10")); // leading space
        assertFalse(ExpiryDate.isValidExpiryDate("2021-10-10 ")); // trailing space
        assertFalse(ExpiryDate.isValidExpiryDate("20a1-10-10")); // alphabets in YYYY
        assertFalse(ExpiryDate.isValidExpiryDate("2020-02-30")); // Invalid expiryDate for february
        assertFalse(ExpiryDate.isValidExpiryDate("20-20-10-10")); // '-' symbol in YYYY
        assertFalse(ExpiryDate.isValidExpiryDate("10-02-2019")); // expiryDate not in YYYY-MM-DD format
        assertFalse(ExpiryDate.isValidExpiryDate("@2010-07-10")); // expiryDate starts with symbol


        // valid expiryDate
        assertTrue(ExpiryDate.isValidExpiryDate("2020-10-10"));
        assertTrue(ExpiryDate.isValidExpiryDate("2020-01-01"));
        assertTrue(ExpiryDate.isValidExpiryDate("0001-10-10"));
        assertTrue(ExpiryDate.isValidExpiryDate("0000-10-10"));

    }
}
