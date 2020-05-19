package framework;

import java.math.BigDecimal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Verifications {
	
	public boolean verify_textbox_is_empty(WebElement textBox) {
		
		String strTextBoxValue = textBox.getAttribute("value");
		
		if (strTextBoxValue.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean verify_value_pattern_in_textBox(WebElement textBox, String valuePattern) {
		
		String strTextBoxValue = textBox.getAttribute("value");
		
		if (strTextBoxValue.trim().matches(valuePattern)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean verify_textbox_value(WebElement textBox, String expectedValue, boolean ignoreCase) {
		
		String strTextBoxValue = textBox.getAttribute("value");
		
		if (ignoreCase) {
			if (strTextBoxValue.equalsIgnoreCase(expectedValue)) {
				return true;
			} else {
				return false;
			}
		} else {
			if (strTextBoxValue.equals(expectedValue)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean verify_checkbox_selected(WebElement checkbox) {
		
		if (checkbox.isSelected()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	public boolean verify_checkbox_is_not_selected(WebElement checkbox) {
		
		if (checkbox.isSelected()) {
			return false;
		} else {
			return true;
		}
		
	}

	public boolean verify_link_text_color(WebElement link, String colorValue) {
		
		String actColor = link.getCssValue("color");
		
		if (actColor.equalsIgnoreCase(colorValue)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean verify_page_title(WebDriver driver, String expTitle) {
		
		String actTitle = driver.getTitle();
		
		if (actTitle.equalsIgnoreCase(expTitle)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean verify_page_title_contains(WebDriver driver, String expTitle) {
		
		String actTitle = driver.getTitle();
		
		if (actTitle.toLowerCase().contains(expTitle.toLowerCase())) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean verify_element_text(WebElement element, String expText) {
		
		String actText = element.getText();
		
		if (actText.equalsIgnoreCase(expText)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean verify_element_text_contains(WebElement element, String expText) {
			
			String actText = element.getText();
			
			if (actText.toLowerCase().contains(expText.toLowerCase())) {
				return true;
			} else {
				return false;
			}
			
	}
	
}
