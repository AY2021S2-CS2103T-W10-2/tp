package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditItemDescriptor;
import seedu.address.model.item.ExpiryDate;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.Location;
import seedu.address.model.item.Phone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditItemDescriptor objects.
 */
public class EditItemDescriptorBuilder {

    private EditCommand.EditItemDescriptor descriptor;

    public EditItemDescriptorBuilder() {
        descriptor = new EditCommand.EditItemDescriptor();
    }

    public EditItemDescriptorBuilder(EditCommand.EditItemDescriptor descriptor) {
        this.descriptor = new EditCommand.EditItemDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditItemDescriptor} with fields containing {@code item}'s details
     */
    public EditItemDescriptorBuilder(Item item) {
        descriptor = new EditCommand.EditItemDescriptor();
        descriptor.setItemName(item.getItemName());
        descriptor.setPhone(item.getPhone());
        descriptor.setExpiryDate(item.getExpiryDate());
        descriptor.setLocation(item.getLocation());
        descriptor.setTags(item.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withName(String name) {
        descriptor.setItemName(new ItemName(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withExpiryDate(String expiryDate) {
        descriptor.setExpiryDate(new ExpiryDate(expiryDate));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditItemDescriptor}
     * that we are building.
     */
    public EditItemDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditItemDescriptor build() {
        return descriptor;
    }
}
