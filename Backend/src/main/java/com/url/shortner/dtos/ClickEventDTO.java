package com.url.shortner.dtos;

import java.time.LocalDate;

public class ClickEventDTO {
private LocalDate clickDate;
private Long count;

public LocalDate getClickDate() {
	return clickDate;
}

public void setClickDate(LocalDate clickDate) {
	this.clickDate = clickDate;
}

@Override
public String toString() {
	return "ClickEventDTO [clickDate=" + clickDate + ", count=" + count + "]";
}

public Long getCount() {
	return count;
}

public void setCount(Long count) {
	this.count = count;
}

}
