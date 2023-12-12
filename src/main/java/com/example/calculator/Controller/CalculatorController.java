package com.example.calculator.Controller;

import org.springframework.stereotype.Controller;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalculatorController {
    @RequestMapping("/")
    public String calculator(Model model) {
        model.addAttribute("result", "");
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(String input, Model model) {
        // Perform calculation
        try {
            double result = evaluateExpression(input);
            model.addAttribute("result", String.valueOf(result));
        } catch (Exception e) {
            model.addAttribute("result", "Error");
        }

        return "index";
    }

    private double evaluateExpression(String expression) throws ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        return ((Number) engine.eval(expression)).doubleValue();
    }
}
