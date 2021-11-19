package org.mutasa.Newspaper.entities;

import java.util.Comparator;

public class ArticleLikesComparator implements Comparator<Article>{
	public int compare(Article a1, Article a2) {
		return Integer.compare(a1.getLikes(), a2.getLikes());
	}
}
