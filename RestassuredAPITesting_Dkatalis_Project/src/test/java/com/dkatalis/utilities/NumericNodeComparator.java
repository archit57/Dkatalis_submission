package com.dkatalis.utilities;

import java.util.Comparator;

import pojo.UserDetails;

public class NumericNodeComparator implements Comparator<UserDetails> {

	@Override
	public int compare(UserDetails o1, UserDetails o2) {

		if (!o1.getData().getId().equals(o2.getData().getId())) {
			return 0;
		}
		if (!o1.getData().getEmail().equals(o2.getData().getEmail())) {
			return 0;
		}
		if (!o1.getData().getFirstname().equals(o2.getData().getFirstname())) {
			return 0;
		}
		if (!o1.getData().getLastname().equals(o2.getData().getLastname())) {
			return 0;
		}
		if (!o1.getData().getAvatar().equals(o2.getData().getAvatar())) {
			return 0;
		}
		if (!o1.getSupport().getUrl().equals(o2.getSupport().getUrl())) {
			return 0;
		}

		if (!o1.getSupport().getText().equals(o2.getSupport().getText())) {
			return 0;
		}

		return 1;
	}

}
