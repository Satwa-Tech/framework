package framework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import exceptions.CheckBoxNotSelectedException;
import exceptions.ElementIsNotClickedException;
import exceptions.ValueNotEnteredIntoTextFieldException;
import exceptions.ValueNotSelectInListBoxException;

public class WebEvents {
	
	private WebDriver driver;
	
	public WebEvents(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * waits till the element is found with given locator within given time out. 
	 * @param by locator to used to find the element
	 * @param timeOutInSeconds max time to wait if the element is not found. 
	 * @return WebElement if the element is found, <b>null</b> will be returned if the element is not found.
	 */
	public WebElement wait_for_element_to_be_found(By by, int timeOutInSeconds) {
		WebElement element = null;
		
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);		
			wait.pollingEvery(Duration.ofMillis(200));
			element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			
			return element;
			
		} catch(NoSuchElementException nse) {
			return null;
		}
	}
	
	public boolean wait_for_element_to_be_enabled(WebElement element, int timeOutInSeconds) {
		
		if (element != null) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);		
				wait.pollingEvery(Duration.ofMillis(200));
				wait.until(ExpectedConditions.elementToBeClickable(element));
				return true;
			} catch(Exception e) {
				return false;
			}
		} 
		
		return false;
	}
	
	public boolean wait_for_element_to_be_enabled(By by, int timeOutInSeconds) {
		
		
			try {
				WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);		
				wait.pollingEvery(Duration.ofMillis(200));
				wait.until(ExpectedConditions.elementToBeClickable(by));
				return true;
			} catch(Exception e) {
				return false;
			}
		
	}
	
	
	public boolean wait_for_element_to_be_displayed(WebElement element, int timeOutInSeconds) {
		
		if (element != null) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);		
				wait.pollingEvery(Duration.ofMillis(200));
				wait.until(ExpectedConditions.visibilityOf(element));
				return true;
			} catch(Exception e) {
				return false;
			}
		}
		
		return false;
	
	}
	
	public boolean wait_for_element_to_be_displayed(By by, int timeOutInSeconds) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);		
				wait.pollingEvery(Duration.ofMillis(200));
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				return true;
			} catch(Exception e) {
				return false;
			}
	}
	
	/**
	 * clicks on the element which has been found with given locator within given time. 
	 * @param by locator to be used to find the element.
	 * @param timeOutInSeconds max time to wait if the element is not found.
	 * @throws ElementIsNotClickedException if the element is not found, not able to click on the element it throws ElemenIsNotClicked Exception.
	 */
	
	public void clickElement(By by, int timeOutInSeconds) throws ElementIsNotClickedException {
		
		WebElement element = wait_for_element_to_be_found(by, timeOutInSeconds);
		if (element != null) {
			if (wait_for_element_to_be_displayed(element, timeOutInSeconds)){
				if (wait_for_element_to_be_enabled(element, timeOutInSeconds)) {					
					element.click();
				} else {
					throw new ElementIsNotClickedException(
							"Clicking on element : " + by.toString()+ " is "
									+ "failed as element is not enabled.");
				}
			} else {
				throw new ElementIsNotClickedException(
						"Clicking on element : " + by.toString()+ " is "
								+ "failed as element is not visible on UI.");
			}
			
		} else {
			throw new ElementIsNotClickedException(
					"Clicking on element : " + by.toString()+ " is "
							+ "failed as element is not found with given locator.");
		}
	}
	
	/**
	 * enters the given value into the text field. If the field contains any default value, value will cleared/over written. 
	 * @param by locator to be used to find the element.
	 * @param input value to be entered.
	 * @param timeOutIntSeconds max time to wait if the element is not found.
	 * @throws ValueNotEnteredIntoTextFieldException if the it fails to enter the value into the field it will throwing ValueNotEnteredIntoTextFieldException.
	 */
	
	public void enter_value_into_text_field(By by, String input, int timeOutInSeconds) throws ValueNotEnteredIntoTextFieldException {
		
		WebElement element = wait_for_element_to_be_found(by, timeOutInSeconds);
		if (element != null) {
			if (wait_for_element_to_be_displayed(element, timeOutInSeconds)){
				if (wait_for_element_to_be_enabled(element, timeOutInSeconds)) {					
					element.clear();
					element.sendKeys(input);
				} else {
					throw new ValueNotEnteredIntoTextFieldException(
							"Entering the value : " + input+ " into the field : " + by.toString()+ " is "
									+ "failed as element is not enabled.");
				}
			} else {
				throw new ValueNotEnteredIntoTextFieldException(
						"Entering the value : " + input+ " into the field : " + by.toString()+ " is "
								+ "failed as element is not Visible on the UI.");
			}
			
		} else {
			throw new ValueNotEnteredIntoTextFieldException(
					"Entering the value : " + input+ " into the field : " + by.toString()+ " is "
							+ "failed as element is not found with given locator..");
		}
		
		
	}
	
	
	/**
	 * selects the checkbox.
	 * 
	 * @param by locator to be used to find the element.
	 * @param timeOutInSeconds max time to wait if the element is not found.
	 * @throws CheckBoxNotSelectedException if the element is not found, not able to click on the element it throws CheckBoxNotSelectedException .
	 */
	public void selectCheckBox(By by, int timeOutInSeconds) throws CheckBoxNotSelectedException {
		WebElement element = wait_for_element_to_be_found(by, timeOutInSeconds);
		if (element != null) {
			if (wait_for_element_to_be_displayed(element, timeOutInSeconds)){
				if (wait_for_element_to_be_enabled(element, timeOutInSeconds)) {
					if (!element.isSelected())
							element.click();
				} else {
					throw new CheckBoxNotSelectedException(
							"selecting the checkbox : " + by.toString()+ " is "
									+ "failed as checkbox is not enabled.");
				}
			} else {
				throw new CheckBoxNotSelectedException(
						"selecting the checkbox : " + by.toString()+ " is "
								+ "failed as checkbox is not visible on UI.");
			}
			
		} else {
			throw new CheckBoxNotSelectedException(
					"selecting the checkbox : " + by.toString()+ " is "
							+ "failed as checkbox is not found with given locator.");
		}
	}
	
	/**
	 * unselects the checkbox.
	 * 
	 * @param by locator to be used to find the element.
	 * @param timeOutInSeconds max time to wait if the element is not found.
	 * @throws CheckBoxNotSelectedException if the element is not found, not able to click on the element it throws CheckBoxNotSelectedException .
	 */
	public void unSelectCheckBox(By by, int timeOutInSeconds) throws CheckBoxNotSelectedException {
		WebElement element = wait_for_element_to_be_found(by, timeOutInSeconds);
		if (element != null) {
			if (wait_for_element_to_be_displayed(element, timeOutInSeconds)){
				if (wait_for_element_to_be_enabled(element, timeOutInSeconds)) {
					if (element.isSelected())
							element.click();
				} else {
					throw new CheckBoxNotSelectedException(
							"unselecting the checkbox : " + by.toString()+ " is "
									+ "failed as checkbox is not enabled.");
				}
			} else {
				throw new CheckBoxNotSelectedException(
						"unselecting the checkbox : " + by.toString()+ " is "
								+ "failed as checkbox is not visible on UI.");
			}
			
		} else {
			throw new CheckBoxNotSelectedException(
					"unselecting the checkbox : " + by.toString()+ " is "
							+ "failed as checkbox is not found with given locator.");
		}
	}
	
	/**
	 * selects the given value in the list box.
	 * 
	 * @param by locator to be used to find the element.
	 * @param timeOutInSeconds max time to wait if the element is not found.
	 * @throws ValueNotSelectInListBoxException if the element is not found, not able to click on the element it throws ValueNotSelectInListBoxException .
	 */
	
	public void select_value_in_list_box(By by, String valueToSelect, int timeOutInSeconds) throws ValueNotSelectInListBoxException {
		
		WebElement listBox = wait_for_element_to_be_found(by, timeOutInSeconds);
		
		if (listBox != null) {
			if (wait_for_element_to_be_displayed(listBox, timeOutInSeconds)){
				if (wait_for_element_to_be_enabled(listBox, timeOutInSeconds)) {					
					int optionIndex = get_option_index_from_list_box(listBox, valueToSelect);
					if (optionIndex >= 0) {
						Select list = new Select(listBox);
						list.selectByIndex(optionIndex);
					} else {
						throw new ValueNotSelectInListBoxException(
								"Selecting the value : " + valueToSelect+" in listbox : " + by.toString()+ " is "
										+ "failed as the value is not found the listbox.");
					}
					
					
				} else {
					throw new ValueNotSelectInListBoxException(
							"Selecting the value : " + valueToSelect+" in listbox : " + by.toString()+ " is "
									+ "failed as the listbox is not enabled.");
				}
			} else {
				throw new ValueNotSelectInListBoxException(
						"Selecting the value : " + valueToSelect+" in listbox : " + by.toString()+ " is "
								+ "failed as the listbox is not displayed on the UI");
			}
			
		} else {
			throw new ValueNotSelectInListBoxException(
					"Selecting the value : " + valueToSelect+" in listbox : " + by.toString()+ " is "
							+ "failed as the listbox is not found.");
		}
		
	}
	
	private int get_option_index_from_list_box(WebElement listBox, String optionValue) {
		
		List<WebElement> allOptions = listBox.findElements(By.tagName("option"));
		
		for (int optionIndex = 0; optionIndex <= allOptions.size()-1; optionIndex++) {			
			WebElement option = allOptions.get(optionIndex);
			if (option.getText().equalsIgnoreCase(optionValue)) {
				return optionIndex;
			}			
		}
				
		return -1;
	}
	
	public void select_multiple_values_in_list_box(By by, String[] allOptionsToSelect, int timeOutInSeconds) throws ValueNotSelectInListBoxException {
		WebElement listBox = wait_for_element_to_be_found(by, timeOutInSeconds);
		
		if (listBox != null) {
			if (wait_for_element_to_be_displayed(listBox, timeOutInSeconds)){
				if (wait_for_element_to_be_enabled(listBox, timeOutInSeconds)) {	
					
					Select list = new Select(listBox);
					String valuesNotFound = "";
					for (String optToSelect:allOptionsToSelect) {						
						int optionIndex = get_option_index_from_list_box(listBox, optToSelect);
						if (optionIndex >= 0)
							list.selectByIndex(optionIndex);
						else
							valuesNotFound = valuesNotFound+";"+optToSelect;
					}
					
					if (!valuesNotFound.isEmpty()) {
						throw new ValueNotSelectInListBoxException(
								"The follwing values : " + valuesNotFound +" in listbox : " + by.toString()+ " is "
										+ " not selected as these values are not found.");
					}
					
					
				} else {
					throw new ValueNotSelectInListBoxException(
							"Selecting the values : " + allOptionsToSelect+" in listbox : " + by.toString()+ " is "
									+ "failed as the listbox is not enabled.");
				}
			} else {
				throw new ValueNotSelectInListBoxException(
						"Selecting the values : " + allOptionsToSelect +" in listbox : " + by.toString()+ " is "
								+ "failed as the listbox is not displayed on the UI");
			}
			
		} else {
			throw new ValueNotSelectInListBoxException(
					"Selecting the values : " + allOptionsToSelect +" in listbox : " + by.toString()+ " is "
							+ "failed as the listbox is not found.");
		}
		
	
	}
		
		
	
	
	

}
