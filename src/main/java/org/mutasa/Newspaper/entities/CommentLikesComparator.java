package org.mutasa.Newspaper.entities;

import java.util.Comparator;

public class CommentLikesComparator implements Comparator<Comment>{
	public int compare(Comment c1, Comment c2) {
		return Integer.compare(c1.getLikes(), c2.getLikes());
	}
}
