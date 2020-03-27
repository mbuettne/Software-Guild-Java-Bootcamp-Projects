USE TrackIt;

SELECT Task.TaskId, Task.Title, TaskStatus.Name
FROM TaskStatus
INNER JOIN Task ON TaskStatus.TaskStatusId = Task.TaskStatusId
WHERE TaskStatus.IsResolved = 1;

SELECT Project.Name, Worker.FirstName, Worker.LastName, Task.Title
FROM Project
INNER JOIN ProjectWorker ON Project.ProjectId = ProjectWorker.ProjectId
INNER JOIN Worker ON ProjectWorker.WorkerId = Worker.WorkerId
INNER JOIN Task ON ProjectWorker.ProjectId = Task.ProjectId 
	AND ProjectWorker.WorkerId = Task.WorkerId
WHERE Project.ProjectId = 'game-goodboy';

SELECT *
FROM Task
LEFT OUTER JOIN TaskStatus 
ON Task.TaskStatusId = TaskStatus.TaskStatusId;

SELECT Project.Name ProjectName
FROM Project
LEFT OUTER JOIN ProjectWorker 
ON Project.ProjectId = ProjectWorker.ProjectId
WHERE ProjectWorker.WorkerId IS NULL;

SELECT Project.Name ProjectName, Worker.FirstName, Worker.LastName
FROM Project 
RIGHT OUTER JOIN ProjectWorker 
ON Project.ProjectId = ProjectWorker.ProjectId
RIGHT OUTER JOIN Worker 
ON ProjectWorker.WorkerId = Worker.WorkerId
WHERE ProjectWorker.ProjectId IS NULL;

SELECT parent.TaskId ParentTaskId, child.TaskId ChildTaskId, 
CONCAT(parent.Title, ': ', child.Title) Title
FROM Task parent
INNER JOIN Task child ON parent.TaskId = child.ParentTaskId;


