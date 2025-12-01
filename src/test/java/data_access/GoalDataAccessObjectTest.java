package data_access;

import entity.Category;
import entity.Goal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoalDataAccessObjectTest {

    @TempDir
    Path tempDir;

    private Path tempFile;
    private GoalDataAccessObject dao;

    @BeforeEach
    void setup() {
        tempFile = tempDir.resolve("goals_test.json");
        dao = new GoalDataAccessObject(tempFile.toString());
    }

    @AfterEach
    void cleanup() {
        File defaultFile = new File("goals.json");
        if (defaultFile.exists()) {
            defaultFile.delete();
        }
    }

    // --- Standard Success Tests ---
// Assuming this test is integrated into your GoalDataAccessObjectTest class structure

    @Test
    void testLoad_IOExceptionTriggered() {
        // Create a directory instead of a file
        Path dirPath = tempDir.resolve("dir_instead_of_file");
        dirPath.toFile().mkdir();

        // Pass directory path as if it were a file
        GoalDataAccessObject daoWithDir = new GoalDataAccessObject(dirPath.toString());

        // It should fall back to empty list and not throw
        assertNotNull(daoWithDir.getAll());
        assertTrue(daoWithDir.getAll().isEmpty());
    }


    @Test
    void testSave_CreatesParentDirectories() {
        // 1. Define a file path that includes non-existent subdirectories
        Path nestedDirPath = tempDir.resolve("nonexistent_folder");
        Path nestedFilePath = nestedDirPath.resolve("goals_deep.json");

        // 2. Instantiate DAO with the deep path
        GoalDataAccessObject daoNested = new GoalDataAccessObject(nestedFilePath.toString());

        // 3. Save a goal (this triggers the save() method)
        Goal g = new Goal(YearMonth.of(2025, 1), 100);
        daoNested.saveGoal(g);

        // 4. Verify that the parent directory was created and the file exists
        assertTrue(Files.exists(nestedDirPath), "The parent directory should have been created.");
        assertTrue(Files.exists(nestedFilePath), "The file should exist within the new directory.");

        // Clean up the created directory afterward (handled by @TempDir, but good practice to check)
        try {
            Files.deleteIfExists(nestedFilePath);
            Files.deleteIfExists(nestedDirPath);
        } catch (IOException e) {
            // Ignore cleanup failure
        }
    }

    @Test
    void testLoad_ThrowsIOException_Handled() {
        // Subclass DAO to simulate IOException
        class FaultyDAO extends GoalDataAccessObject {
            public FaultyDAO(String path) {
                super(path);
            }

            @Override
            protected FileReader createFileReader() throws IOException {
                throw new IOException("Simulated IO failure");
            }
        }

        GoalDataAccessObject dao = new FaultyDAO(tempDir.resolve("dummy.json").toString());

        // After load fails, the goals list should be empty
        assertNotNull(dao.getAll());
        assertTrue(dao.getAll().isEmpty());
    }

    @Test
    void testLoad_HandlesNullJsonFileContent() throws IOException {
        // 1. Write the literal JSON value 'null' to the file
        // Gson will parse this successfully, resulting in the List<Goal> being null.
        Files.writeString(tempFile, "null");

        // 2. Load with a new DAO instance
        GoalDataAccessObject dao2 = new GoalDataAccessObject(tempFile.toString());

        // 3. Verify that the null check triggered the initialization to an empty ArrayList
        assertNotNull(dao2.getAll());
        assertTrue(dao2.getAll().isEmpty());
    }


    @Test
    void testSaveAndLoad_Success() {
        YearMonth ym = YearMonth.of(2025, 1);
        Goal g = new Goal(ym, 500);

        dao.saveGoal(g);

        GoalDataAccessObject dao2 = new GoalDataAccessObject(tempFile.toString());
        List<Goal> loadedGoals = dao2.getAll();

        assertEquals(1, loadedGoals.size());
        assertEquals(ym, loadedGoals.get(0).getMonth());
        assertEquals(500, loadedGoals.get(0).getGoalAmount());
    }

    @Test
    void testSaveGoal_UpdatesExisting() {
        // 1. Save initial
        Goal original = new Goal(YearMonth.of(2025, 1), 100);
        dao.saveGoal(original);

        // 2. Save update
        Goal update = new Goal(YearMonth.of(2025, 1), 999);
        dao.saveGoal(update);

        GoalDataAccessObject dao2 = new GoalDataAccessObject(tempFile.toString());
        assertEquals(1, dao2.getAll().size());
        assertEquals(999, dao2.getAll().get(0).getGoalAmount());
    }

    // --- Edge Case / Exception Tests ---

    @Test
    void testLoad_EmptyFile_ReturnsEmptyList() throws IOException {
        Files.createFile(tempFile);
        GoalDataAccessObject dao2 = new GoalDataAccessObject(tempFile.toString());
        assertTrue(dao2.getAll().isEmpty());
    }

    @Test
    void testLoad_NoFile_ReturnsEmptyList() {
        Path nonExistent = tempDir.resolve("ghost.json");
        GoalDataAccessObject dao2 = new GoalDataAccessObject(nonExistent.toString());
        assertTrue(dao2.getAll().isEmpty());
    }

    @Test
    void testSave_ThrowsRuntimeException_WhenWriteFails() {
        // Create a directory where a file should be
        // This forces new FileWriter() to fail with IOException
        Path badPath = tempDir.resolve("read_only_dir");
        badPath.toFile().mkdir();

        GoalDataAccessObject daoBad = new GoalDataAccessObject(badPath.toString());
        Goal g = new Goal(YearMonth.of(2025, 1), 100);

        // Verify the RuntimeException wrapper is thrown
        Exception exception = assertThrows(RuntimeException.class, () -> {
            daoBad.saveGoal(g);
        });

        assertEquals("Failed to save goals", exception.getMessage());
    }

    @Test
    void testRemoveIfExecutesWithCategories() {
        // Create a category
        Category cat = new Category("Food");

        // Save initial goal with a category
        Goal g1 = new Goal(YearMonth.of(2025, 1), List.of(cat), 100);
        dao.saveGoal(g1);

        // Save another goal with the same month and same category
        Goal g2 = new Goal(YearMonth.of(2025, 1), List.of(cat), 999);
        dao.saveGoal(g2);

        List<Goal> allGoals = dao.getAll();

        // Only one goal should exist, the second one replaced the first
        assertEquals(1, allGoals.size());
        assertEquals(999, allGoals.get(0).getGoalAmount());
    }

    @Test
    void testDefaultConstructor() {
        GoalDataAccessObject defaultDao = new GoalDataAccessObject();
        assertNotNull(defaultDao.getAll());
    }
}