package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.ItemComparatorByQuantity;

public class SortQuantityCommand extends SortCommand {

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Item> lastShownList = model.getFilteredItemList();

        if (lastShownList.size() == 0) {
            throw new CommandException(MESSAGE_NO_ITEMS_TO_SORT);
        }

        ItemComparatorByQuantity comparator = new ItemComparatorByQuantity();
        model.updateSortedItemList(comparator);
        model.setItems(model.getSortedItemList());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SortQuantityCommand);
    }
}