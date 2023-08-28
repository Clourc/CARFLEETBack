package com.project.carfleet.controller;

import java.util.Optional;

import javax.swing.text.html.Option;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.project.carfleet.entity.Model;
import com.project.carfleet.repository.ModelRepository;

@Controller
public class ModelController {

    @Autowired
    private ModelRepository modelRepository;

    @GetMapping("/models")
    @ResponseBody
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    @GetMapping("/models/{id}")
    @ResponseBody
    public Model getModelById(@PathVariable Long id) {
        Optional<Model> model = modelRepository.findById(id);
        if (model.isPresent()) {
            return model.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "model not found");
        }
    }

    @PostMapping("/models")
    @ResponseBody
    public String postmodels(@RequestBody Model model) {
        modelRepository.save(model);
        return "save ok";
    }

    @DeleteMapping("/models/delete/{id}")
    @ResponseBody
    public String deletemodel(@PathVariable(value = "id") Long modelId) {
        modelRepository.deleteById(modelId);
        return "delete ok";
    }

}
