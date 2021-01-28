package com.project.corona.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PongController {

    class Obj {
        String date;
        int y;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Obj(String date, int y) {
            this.date = date;
            this.y = y;

        }
    }

    class Test implements Serializable {
        ArrayList<Obj> dataPoints;
        String name;

        public ArrayList<Obj> getDataPoints() {
            return dataPoints;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDataPoints(ArrayList<Obj> dataPoints) {
            this.dataPoints = dataPoints;
        }

        public Test(ArrayList<String> test) {
            name = String.join(",", test);
            if (test.contains("vaka")) {
                dataPoints = new ArrayList<>();
                dataPoints.add(new Obj(("02.01.2017"), 42800));
                dataPoints.add(new Obj(("03.01.2017"), 41800));
                dataPoints.add(new Obj(("04.01.2017"), 38800));
                dataPoints.add(new Obj(("05.01.2017"), 46800));
                dataPoints.add(new Obj(("06.01.2017"), 42000));
            } else {
                dataPoints = new ArrayList<>();
                dataPoints.add(new Obj(("02.01.2018"), 128));
                dataPoints.add(new Obj(("03.01.2018"), 918));
                dataPoints.add(new Obj(("04.01.2018"), 588));
                dataPoints.add(new Obj(("05.01.2018"), 268));
                dataPoints.add(new Obj(("06.01.2018"), 420));

            }
        }

        public Test(int i, String tur, int i1) {
            dataPoints = new ArrayList<>();
            name = tur;
            if (i1 == 1) {
                if (i == 1) {
                    dataPoints.add(new Obj(("02.01.2017"), 428));
                    dataPoints.add(new Obj(("03.01.2017"), 418));
                    dataPoints.add(new Obj(("04.01.2017"), 388));
                    dataPoints.add(new Obj(("05.01.2017"), 468));
                    dataPoints.add(new Obj(("06.01.2017"), 420));
                } else if (i == 2) {
                    dataPoints.add(new Obj(("02.01.2017"), 128));
                    dataPoints.add(new Obj(("03.01.2017"), 918));
                    dataPoints.add(new Obj(("04.01.2017"), 588));
                    dataPoints.add(new Obj(("05.01.2017"), 268));
                    dataPoints.add(new Obj(("06.01.2017"), 418));
                } else {
                    dataPoints.add(new Obj(("02.01.2017"), 333));
                    dataPoints.add(new Obj(("03.01.2017"), 444));
                    dataPoints.add(new Obj(("04.01.2017"), 122));
                    dataPoints.add(new Obj(("05.01.2017"), 345));
                    dataPoints.add(new Obj(("06.01.2017"), 215));
                }
            } else {
                if (i == 1) {

                    dataPoints.add(new Obj(("02.01.2019"), 428));
                    dataPoints.add(new Obj(("03.01.2019"), 418));
                    dataPoints.add(new Obj(("04.01.2019"), 388));
                    dataPoints.add(new Obj(("05.01.2019"), 468));
                    dataPoints.add(new Obj(("06.01.2019"), 420));
                } else if (i == 2) {
                    dataPoints.add(new Obj(("02.01.2019"), 128));
                    dataPoints.add(new Obj(("03.01.2019"), 918));
                    dataPoints.add(new Obj(("04.01.2019"), 588));
                    dataPoints.add(new Obj(("05.01.2019"), 268));
                    dataPoints.add(new Obj(("06.01.2019"), 418));
                } else {
                    dataPoints.add(new Obj(("02.01.2019"), 333));
                    dataPoints.add(new Obj(("03.01.2019"), 444));
                    dataPoints.add(new Obj(("04.01.2019"), 122));
                    dataPoints.add(new Obj(("05.01.2019"), 345));
                    dataPoints.add(new Obj(("06.01.2019"), 215));
                }
            }
        }
    }

    private class Test2 {
        private int value;
        private String label;

        public Test2(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    @RequestMapping(value = "/haber_gonder", method = RequestMethod.POST)
    public String haberGonder(@RequestBody String test) {
        System.out.println(test.substring(0, test.length() - 1));
        return "done";
    }

    @RequestMapping(value = "/sehir_gonder", method = RequestMethod.POST)
    public ResponseEntity<Test[]> sehirGonder(@RequestBody String test) {
        Test[] test_arr = new Test[3];
        test_arr[0] = new Test(0, "Vaka", 2);
        test_arr[1] = new Test(1, "Vefat", 2);
        test_arr[2] = new Test(2, "İyileşen", 2);
        return new ResponseEntity<Test[]>(test_arr, HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<Test[]> test() {
        Test[] test_arr = new Test[3];
        test_arr[0] = new Test(0, "Vaka", 1);
        test_arr[1] = new Test(1, "Vefat", 1);
        test_arr[2] = new Test(2, "İyileşen", 1);
        return new ResponseEntity<Test[]>(test_arr, HttpStatus.OK);
    }

    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Test2>> test3() {
        ArrayList<Test2> arr = new ArrayList<Test2>(
                Arrays.asList(
                        new Test2(1, "Ankara"),
                        new Test2(2, "Istanbul"),
                        new Test2(3, "Antalya")));
        System.out.println(arr);
        return new ResponseEntity<ArrayList<Test2>>(arr, HttpStatus.OK);
    }

}






