#!/usr/bin/env node
const {Builder, By, Key, until} = require('selenium-webdriver');

(async function stackoverflow() {
	let driver = await new Builder()
	    	    .forBrowser('firefox').build();
	
	await driver.get('https://www.stackoverflow.com/');

	await driver.findElement(By.name('q')).sendKeys('[selenium]',Key.RETURN);

	let qs = await driver.wait(until.elementsLocated(By.className('question-summary')));
	//console.log(qs);
	for(let e of qs) {
		//console.log(await e.getText());
		let titleElem = await e.findElement(By.className("question-hyperlink"));
		let descElem = await e.findElement(By.className("excerpt"));
		let userElem = await e.findElement(By.className("user-details"));

		let title = await titleElem.getText();
		let link = await titleElem.getAttribute("href");
		let desc = await descElem.getText();
		let user = await userElem.getText();

		console.log(title + " --> " + user + " --> " + link);
	}

})();
