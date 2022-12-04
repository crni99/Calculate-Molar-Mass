package com.crni99.calculatemolarmass.controller;

import java.util.ArrayList;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crni99.calculatemolarmass.model.Element;
import com.crni99.calculatemolarmass.repository.Repository;

@org.springframework.stereotype.Controller
public class Controller {

	private final Repository repository;

	public Controller(Repository repository) {
		this.repository = repository;
	}

	@GetMapping(value = { "", "/" })
	public String home() {
		return "index";
	}

	@GetMapping("/calculate/")
	public String calculate(@RequestParam(value = "formula", required = true) String formula, Model model)
			throws Exception {

		if (formula.isEmpty() || formula.isBlank() || formula == null) {
			model.addAttribute("result", "The formula is incorrect!");
			return "index";
		}

		ArrayList<String> elements = new ArrayList<String>();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		float sum = 0;
		int upperCase;
		int lowerCase;
		String elem;
		int count;
		String numb;
		String search;
		int number;
		String result = null;

		elements.removeAll(elements);
		numbers.removeAll(numbers);

		upperCase = 0;
		lowerCase = 0;
		elem = "";

		count = 0;
		numb = "";

		int i;
		char c = 0;
		for (i = 0; i < formula.length(); i++) {
			c = formula.charAt(i);
			if (Character.isUpperCase(c)) {
				if (i > 0) {
					if (upperCase == 1) {
						elements.add(elem);
						elem = "";

						if (count == 0) {
							numbers.add(1);
							numb = "";
						} else if (count == 1) {
							numbers.add(Integer.parseInt(numb));
							numb = "";
						}
					} else if (lowerCase == 1) {
						if (count == 0) {
							numbers.add(1);
							numb = "";
						} else if (count == 1) {
							numbers.add(Integer.parseInt(numb));
							numb = "";
						}
						elements.add(elem);
						elem = "";
					} else if (count == 1) {
						numbers.add(Integer.parseInt(numb));
						numb = "";
					}
				}
				upperCase = 1;
				lowerCase = 0;
				count = 0;
				elem = elem + c;
			} else if (Character.isLowerCase(c)) {
				upperCase = 0;
				lowerCase = 1;
				count = 0;
				elem = elem + c;
			} else if (Character.isDigit(c)) {
				if (upperCase == 1 || lowerCase == 1) {
					elements.add(elem);
					elem = "";
				}
				upperCase = 0;
				lowerCase = 0;
				count = 1;
				numb = numb + c;
			}
		}
		if (i == formula.length()) {
			if (Character.isUpperCase(c) || Character.isLowerCase(c)) {
				elements.add(elem);
				numbers.add(1);
			} else if (Character.isDigit(c)) {
				numbers.add(Integer.parseInt(numb));
			}
		}
		for (int j = 0; j < elements.size(); j++) {
			search = elements.get(j);
			Element elemDb = repository.findElementByName(search);

			if (elemDb == null) {
				model.addAttribute("result", "Тhe requested element does not exist!");
				return "index";
			} else {
				number = elemDb.getAtomicMass();
				sum = sum + numbers.get(j) * number;
			}
		}
		if (sum != 0) {
			result = Float.toString(sum);
			model.addAttribute("formula", formula);
			model.addAttribute("result", result);
		} else {
			model.addAttribute("result", "Error!");
		}
		return "index";
	}

}
