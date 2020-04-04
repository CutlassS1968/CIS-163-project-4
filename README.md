# CIS-163-project-4
Project 4 for CIS 163 at GVSU 

Due Date:
    April 14, 2020

Before Starting the Project:
    - Review Chapters 8 - 10 and Chapters 12, 13, 15, 18 of the CIS163 book
    - Read this entire project description before starting, if you have and questions please ask the instructor

Why are you doing this assignment:
    - This project will help you develop complex methods, that is, the add method will be very complex to implement

Learning Objectives:
    After completing this project, you should be able to:
        - Implement a Single Linked list
        - Have the ability to examine and figure out existing code and modify that code

Program Description:
    Your first task as a new programmer in a company will typically be modifying existing code. With that in mind,
    our assignment is to change the Reservation System program (provided, see Project 4 starting code on BB; this code
    is almost exactly the code you started with in project 3) so the program uses a Linked list and other new
    functionality. You cannot add any additional instance variables to the classes provided without the instructor's
    permission, specifically, the Node.java and MySingleWithOutTailLinkedList.java

TODO Step 0 - Start with the code provided and figure out how this code functions (take an hour or two)

TODO Step 1 - The existing code uses an ArrayList, change the following:
    In listModel, change all the ArrayList's to MySingleWithOutTailLinkedList (USE a single linked list without a tail).
    Once you have made this change, you will not see syntax errors in every place an ArrayList method was being used.
    However, your program will not function and your task is to complete steps 2, and 3. From this point on, you are not
    permitted to change the ListModel class.

TODO Step 2 - Complete all the methods found in MySingleWithOutTailLinkedList:
    Complete all the methods found in MySingleWithOutTailLinkedList class and any additional methods you need. You
    cannot add any additional instance variables to the classes provided, that is, the Node, and/or the
    MySingleWithOutTailLinkedList classes. You are NOT permitted to change the GUI and ListModel class. Most of your
    code should be in MySingleWithOutTailLinkedList and MySingleWithOutTailLinkedListTest classes.

  Requirements for step 2:
      When you write the add method, you are required to sort by tenters first (ordered by estimatedCheckOut) and by RV
      second (ordered by estimatedCheckOut). For this step 1, you need not worry about two estimatedCheckOut dates
      being equals. (See the final step regarding a change in this requirement).

TODO Step 3 - Handle equals cases in the add method:
    If two dates are the same then sort by guest name, this is a difficult step and you will need to see the instructor
    before attempting this step.



Black Board:
    Interesting Code:

        public void createList() {
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            GregorianCalendar g1 = new GregorianCalendar();
            GregorianCalendar g2 = new GregorianCalendar();

            try {
                Date d1 = df.parse("1/1/2020");
                g1.setTime(d1);
                Date d2 = df.parse("1/4/2020");
                g2.setTime(d2);

                TentOnly tentOnly1 = new TentOnly("T1", g1, g2,g2,4);
                TentOnly tentOnly2 = new TentOnly("T2", g1,g2,g1, 8);

                RV RV1 = new RV("RV1",g1,g2,g2, 1000);
                RV RV2 = new RV("RV2",g1,g2,g1, 1000);

                add(tentOnly1);
                add(tentOnly2);

                add(RV1);
                add(RV2);

                // create a bunch of them.
                int count = 0;
                Random rand = new Random(13);
                String guest = null;

                while (count < 30) {
                    Date date = df.parse("1/" + (rand.nextInt(10) + 2) + "/2020");
                    GregorianCalendar g = new GregorianCalendar();
                    g.setTime(date);
                    if (rand.nextBoolean()) {
                        guest = "T" + rand.nextInt(5);
                        TentOnly tent = new TentOnly(guest, g1, g, null, rand.nextInt(20));
                        add(tent);
                    } else {
                        guest = "RV" + rand.nextInt(5);
                        date = df.parse("1/" + (rand.nextInt(10) + 2) + "/2020");
                        g.setTime(date);
                        RV rv = new RV(guest, g1, g, null, rand.nextInt(2000));
                        add(rv);
                    }

                    count++;
                }

            } catch (ParseException e) {
                throw new RuntimeException("Error in testing, creation of list");
            }

        }
