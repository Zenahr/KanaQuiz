package com.noprestige.kanaquiz.questions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WeightedListTest
{
    private WeightedList<String> demoList(int[] weights, String[] strings)
    {
        WeightedList<String> list = new WeightedList<>();
        final int SAMPLE_COUNT = weights.length;
        assertEquals(strings.length, SAMPLE_COUNT);

        for (int i = 0; i < SAMPLE_COUNT; i++)
            list.add(weights[i], strings[i]);

        return list;
    }

    private int testList(int i, int[] weights, String[] strings, WeightedList<String> list)
    {
        final int SAMPLE_COUNT = weights.length;
        assertEquals(strings.length, SAMPLE_COUNT);

        for (int j = 0; j < SAMPLE_COUNT; j++)
            for (int start = i; i < weights[j] + start; i++)
                assertEquals(list.get(i), strings[j]);

        return i;
    }

    @Test
    public void rangeTest() throws Exception
    {
        int[] weights = new int[]{3, 8, 27, 6};
        String[] strings = new String[]{"Bleh", "Foo", "Snide", "Mesh"};
        WeightedList<String> list = demoList(weights, strings);

        int i = 0;
        i = testList(i, weights, strings, list);
        assertEquals(list.maxValue(), i);

        try
        {
            list.get(i);
            assertTrue(false);
        }
        catch (IndexOutOfBoundsException e)
        {
        }
    }

    @Test
    public void mergeTest() throws Exception
    {
        int[] weightsOne = new int[]{3, 8, 27, 6};
        String[] stringsOne = new String[]{"Bleh", "Foo", "Snide", "Mesh"};
        WeightedList<String> listOne = demoList(weightsOne, stringsOne);

        int[] weightsTwo = new int[]{8, 2, 9, 21};
        String[] stringsTwo = new String[]{"Gree", "Fnex", "Moose", "Twa"};
        WeightedList<String> listTwo = demoList(weightsTwo, stringsTwo);

        WeightedList<String> listMerged = new WeightedList<>();

        listMerged.merge(listOne);
        listMerged.merge(listTwo);

        int i = 0;
        i = testList(i, weightsOne, stringsOne, listMerged);
        i = testList(i, weightsTwo, stringsTwo, listMerged);
        assertEquals(listMerged.maxValue(), i);

        try
        {
            listMerged.get(i);
            assertTrue(false);
        }
        catch (IndexOutOfBoundsException e)
        {
        }
    }
}