package com.company.board;

import java.util.List;

public interface BoardStrategy {

    List<List<String>> generate(int size);

}
