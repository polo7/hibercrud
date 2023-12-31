package dev.lesechko.hibercrud.controller;

import java.util.List;

import dev.lesechko.hibercrud.model.Label;
import dev.lesechko.hibercrud.model.Status;
import dev.lesechko.hibercrud.service.LabelService;

public class LabelController {
    private final LabelService labelService = new LabelService();

    public Label add(String labelName) {
        Label newLabel = new Label();
        newLabel.setName(labelName);
        newLabel.setStatus(Status.ACTIVE);
        return labelService.save(newLabel);
    }

    public List<Label> getAll() {
        return labelService.getAll();
    }

    public Label getById(Long id) {
        return labelService.getById(id);
    }

    public Label update(Label label, String newName, boolean changeStatus) {
        boolean changeName = (newName != null && !newName.isEmpty());
        if (!changeName && !changeStatus) return label;
        if (changeName) label.setName(newName);
        if (changeStatus) {
            Status newStatus = (label.getStatus() == Status.DELETED) ? Status.ACTIVE : Status.DELETED;
            label.setStatus(newStatus);
        }
        return labelService.update(label);
    }

    public boolean deleteById(Long id) {
        return labelService.deleteById(id);
    }
}
