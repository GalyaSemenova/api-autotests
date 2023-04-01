package timetable.tests;

import timetable.api.PlacedLessons;

public class Total {
    static ClassroomTypeTest ctt;
    static ClassroomTest ct;
    static BellGridTest bgt;
    static WeeksTest wt;
    static IntervalsTest it;
    static SubjectsTest st;
    static AllowedClassroomTypesTest actt;
    static DifficultyPointsTest dpt;
    static TeachersTest tt;
    static TeacherPriorityClassroomsTest tpct;
    static CurriculumDivisionTypesTest cdtt;
    static CurriculumDivisionSubgroupsTest cdst;
    static AnnualCurriculumsTest act;
    static AcademicHoursTest aht;
    static GroupsTest gt;
    static GroupPriorityClassroomsTest gpct;
    static SubgroupTeachersTest stt;
    static TimetablesTest ttt;
    static LessonsTest lt;


    public static void main(String[] args) throws Exception {
        ctt = new ClassroomTypeTest();
        ct = new ClassroomTest();

        bgt = new BellGridTest();
        wt = new WeeksTest();
        it = new IntervalsTest();

        st = new SubjectsTest();
        actt = new AllowedClassroomTypesTest();
        dpt = new DifficultyPointsTest();

        tt = new TeachersTest();
        tpct = new TeacherPriorityClassroomsTest();

        cdtt = new CurriculumDivisionTypesTest();
        cdst = new CurriculumDivisionSubgroupsTest();
        act = new AnnualCurriculumsTest();
        aht = new AcademicHoursTest();

        gt = new GroupsTest();
        gpct = new GroupPriorityClassroomsTest();
        stt = new SubgroupTeachersTest();

        ttt = new TimetablesTest();
        lt = new LessonsTest();

        create();
//        delete();
    }

    public static void create() throws  Exception {

        ctt.createTest();
        ct.createTest();

        bgt.createTest();
        wt.createTest();
        it.createTest();

        st.createTest();
        actt.createTest();
        dpt.createTest();

        tt.createTest();
        tpct.createTest();

        cdtt.createTest();
        cdst.createTest();
        act.createTest();
        aht.createTest();

        gt.createTest();
        gpct.createTest();
        stt.createTest();

        ttt.createTest();
        lt.createTest();
    }

    public static void delete() throws  Exception {
        lt.deleteTest();

        stt.deleteTest();
        gpct.deleteTest();
        gt.deleteTest();

        aht.deleteTest();
        act.deleteTest();
        cdst.deleteTest();
        cdtt.deleteTest();

        tpct.deleteTest();
        tt.deleteTest();

        dpt.deleteTest();
        actt.deleteTest();
        st.deleteTest();

        it.deleteTest();
        wt.deleteTest();
        bgt.deleteTest();

        ct.deleteTest();
        ctt.deleteTest();

        ttt.deleteTest();

    }
}
