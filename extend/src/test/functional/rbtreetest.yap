:-use_module(library(rbtrees)).
:-use_module(library(swi)).
:-use_module(library(system)).
:-use_module(library(lists)).


random_tree(T, N):- rb_new(RT), random_insert(N, RT, T).
random_insert(0, T, T):- !.
random_insert(N, T, TF):- !,
         N1 is N-1,
         rb_insert(T, N, N, T1),
         random_insert(N1, T1, TF).

test_remove_max(0, _):-!.
test_remove_max(N, T):- !,
         N1 is N-1,
	 rb_del_max(T,Key,Val,T1),
         test_remove_max(N1, T1).

benchmark:- random_tree(RT, 1000000), test_remove_max(1000000, RT).

% borrowed from http://www.cs.cmu.edu/afs/cs/project/ai-repository/ai/lang/prolog/code/ext/interval/0.html

interval_is_disjoint_from_num((L1,U1), Num):-
           U1 < Num, !.

interval_is_disjoint_from_num((L1,U1), Num):-
           Num < L1, !.

intervals_are_disjoint((L1,U1), (L2,U2)):-
           U1 < L2, !.

intervals_are_disjoint( (L1,U1), (L2,U2) ) :-
           U2 < L1, !.

interval_intersects_num(I1, Num):-
	not( interval_is_disjoint_from_num(I1,Num) ), !.

intervals_intersect(I1, I2):-
	   (Key, Val) = I1,
	   (Key = [] ->
	      fail
	      ;
              not( intervals_are_disjoint(I1,I2) )
           ).

interval_intersection( (L1,U1), (L2,U2), (L3,U3) ) :-
    max( L1, L2, L3 ),
    min( U1, U2, U3 ).

/*  max( A+, B+, M- ):

    M is the maximum of A and B.
*/
max( A, B, A ) :- A > B, !.
max( A, B, B ).


/*  min( A+, B+, M- ):

    M is the minimum of A and B.
*/
min( A, B, A ) :- A < B, !.
min( A, B, B ).

%Subtract 1 from 2

interval_subtract( (L1,U1), (L1,U1), []):-!.

interval_subtract( (L1,U1), (L1,U2), IntervalList):-
	LowerBound is U1 + 1,
	IntervalList = [(LowerBound, U2)], !.
	
interval_subtract( (L1,U2), (L2,U2), IntervalList):-
	UpperBound is L1 - 1,
	IntervalList = [(L2,UpperBound)], !.
	
interval_subtract( (L1,U1), (L2,U2), IntervalList):-
	UpperBound1 is L1 - 1,
	LowerBound2 is U1 + 1,
	IntervalList = [(L2, UpperBound1), (LowerBound2,U2)], !.


interval_length((L, U), Dur):-
	InitialDur is U - L + 1,
        (InitialDur < 0 ->
	   Dur is InitialDur * -1
           ;
	   Dur is InitialDur
        ).


get_taskduration_remaining(Task, ResourceIntersection, TaskDurRemaining):-
	interval_length(Task, TaskDuration),
	interval_length(ResourceIntersection, ResourceIntersectionDuration),
	TaskDurRemaining is TaskDuration - ResourceIntersectionDuration.

get_remaindertask(Task, ResourceIntersection, TaskDurRemaining, RemainderTask):-
	(TaskLow, TaskHigh) = Task,
	(RILow, RIHigh) = ResourceIntersection,
	RTLow is RIHigh + 1,
	%RTHigh is RTLow + TaskDurRemaining - 1, 
	RTHigh is TaskDurRemaining - 1, 
	RemainderTask = (RTLow, RTHigh).


get_intersecting_intervals(IntervalToIntersect, [], [], FinalBFSKeys, FinalBFSKeys, FinalIntersectingIntervals, FinalIntersectingIntervals):-!.

get_intersecting_intervals(IntervalToIntersect, [], NewNodeList, TempBFSKeys, FinalBFSKeys, TempIntersectingIntervals, FinalIntersectingIntervals):-
        get_intersecting_intervals(IntervalToIntersect, NewNodeList, [], TempBFSKeys, FinalBFSKeys, TempIntersectingIntervals, FinalIntersectingIntervals),!.

get_intersecting_intervals(IntervalToIntersect, NodeList, NewNodeList, TempBFSKeys, FinalBFSKeys, TempIntersectingIntervals, FinalIntersectingIntervals):-
        %write('Here 1'),
        [Node|Ns] = NodeList,
        %write('Here 2'),
        %write('Node: '),
        %write(Node),
        black(L, K, V, R) = Node,
        K = [],
        get_intersecting_intervals(IntervalToIntersect, Ns, NewNodeList, TempBFSKeys, FinalBFSKeys, TempIntersectingIntervals, FinalIntersectingIntervals), !.

get_intersecting_intervals(IntervalToIntersect, NodeList, NewNodeList, TempBFSKeys, FinalBFSKeys, TempIntersectingIntervals, FinalIntersectingIntervals):-
        %write('Here 3'),
        [Node|Ns] = NodeList,
        %write('Here 4'),
        %write('Node: '),
        %write(Node),
        black(L, K, V, R) = Node,
        L = [],
        R = [],
        write('Here 5'),
        append(TempBFSKeys, [K], NewTempBFSKeys),
	(Lo, Hi) = IntervalToIntersect,
	(intervals_intersect((K, V), (Lo, Hi))->
           append(TempIntersectingIntervals, [(K, V)], NewTempIntersectingIntervals)
	   ;
           NewTempIntersectingIntervals = TempIntersectingIntervals
        ),
        %write('Here 6'),
        get_intersecting_intervals(IntervalToIntersect, Ns, NewNodeList, NewTempBFSKeys, FinalBFSKeys, NewTempIntersectingIntervals, FinalIntersectingIntervals), !.

get_intersecting_intervals(IntervalToIntersect, NodeList, NewNodeList, TempBFSKeys, FinalBFSKeys, TempIntersectingIntervals, FinalIntersectingIntervals):-
        %write('Here 7'),
        [Node|Ns] = NodeList,
        %write('Here 8'),
        %write('Node: '),
        %write(Node),
        red(L, K, V, R) = Node,
        L = [],
        R = [],
        %write('Here 9'),
        append(TempBFSKeys, [K], NewTempBFSKeys),
        %write('Here 10'),
	(Lo, Hi) = IntervalToIntersect,
	(intervals_intersect((K, V), (Lo, Hi))->
           append(TempIntersectingIntervals, [(K, V)], NewTempIntersectingIntervals)
	   ;
           NewTempIntersectingIntervals = TempIntersectingIntervals
        ),
        get_intersecting_intervals(IntervalToIntersect, Ns, NewNodeList, NewTempBFSKeys, FinalBFSKeys, NewTempIntersectingIntervals, FinalIntersectingIntervals), !.

get_intersecting_intervals(IntervalToIntersect, NodeList, NewNodeList, TempBFSKeys, FinalBFSKeys, TempIntersectingIntervals, FinalIntersectingIntervals):-
        %write('Here 11'),
        [Node|Ns] = NodeList,
        %write('Here 12'),
        %write('Node: '),
        %write(Node),
        red(L, K, V, R) = Node,
        L = [],
        %write('Here 13'),
        append(TempBFSKeys, [K], NewTempBFSKeys),
        append(NewNodeList, [R], NewNewNodeList),
        %write('Here 14'),
	(Lo, Hi) = IntervalToIntersect,
	(intervals_intersect((K, V), (Lo, Hi))->
           append(TempIntersectingIntervals, [(K, V)], NewTempIntersectingIntervals)
	   ;
           NewTempIntersectingIntervals = TempIntersectingIntervals
        ),
        get_intersecting_intervals(IntervalToIntersect, Ns, NewNewNodeList, NewTempBFSKeys, FinalBFSKeys, NewTempIntersectingIntervals, FinalIntersectingIntervals), !.

get_intersecting_intervals(IntervalToIntersect, NodeList, NewNodeList, TempBFSKeys, FinalBFSKeys, TempIntersectingIntervals, FinalIntersectingIntervals):-
        %write('Here 15'),
        [Node|Ns] = NodeList,
        %write('Here 16'),
        %write('Node: '),
        %write(Node),
        black(L, K, V, R) = Node,
        L = [],
        %write('Here 17'),
        append(TempBFSKeys, [K], NewTempBFSKeys),
        append(NewNodeList, [R], NewNewNodeList),
        write('Here 18'),
	(Lo, Hi) = IntervalToIntersect,
	(intervals_intersect((K, V), (Lo, Hi))->
           append(TempIntersectingIntervals, [(K, V)], NewTempIntersectingIntervals)
	   ;
           NewTempIntersectingIntervals = TempIntersectingIntervals
        ),
        get_intersecting_intervals(IntervalToIntersect, Ns, NewNewNodeList, NewTempBFSKeys, FinalBFSKeys, NewTempIntersectingIntervals, FinalIntersectingIntervals), !.

get_intersecting_intervals(IntervalToIntersect, NodeList, NewNodeList, TempBFSKeys, FinalBFSKeys, TempIntersectingIntervals, FinalIntersectingIntervals):-
        %write('Here 19'),
        [Node|Ns] = NodeList,
        %write('Here 20'),
        %write('Node: '),
        %write(Node),
        red(L, K, V, R) = Node,
        R = [],
        %write('Here 21'),
        append(TempBFSKeys, [K], NewTempBFSKeys),
        append(NewNodeList, [L], NewNewNodeList),
        %write('Here 22'),
	(Lo, Hi) = IntervalToIntersect,
	(intervals_intersect((K, V), (Lo, Hi))->
           append(TempIntersectingIntervals, [(K, V)], NewTempIntersectingIntervals)
	   ;
           NewTempIntersectingIntervals = TempIntersectingIntervals
        ),
        get_intersecting_intervals(IntervalToIntersect, Ns, NewNewNodeList, NewTempBFSKeys, FinalBFSKeys, NewTempIntersectingIntervals, FinalIntersectingIntervals), !.

get_intersecting_intervals(IntervalToIntersect, NodeList, NewNodeList, TempBFSKeys, FinalBFSKeys, TempIntersectingIntervals, FinalIntersectingIntervals):-
        %write('Here 23'),
        [Node|Ns] = NodeList,
        %write('Here 24'),
        %write('Node: '),
        %write(Node),
        black(L, K, V, R) = Node,
        R = [],
        %write('Here 25'),
        append(TempBFSKeys, [K], NewTempBFSKeys),
        append(NewNodeList, [L], NewNewNodeList),
        %write('Here 26'),
	(Lo, Hi) = IntervalToIntersect,
	(intervals_intersect((K, V), (Lo, Hi))->
           append(TempIntersectingIntervals, [(K, V)], NewTempIntersectingIntervals)
	   ;
           NewTempIntersectingIntervals = TempIntersectingIntervals
        ),
        get_intersecting_intervals(IntervalToIntersect, Ns, NewNewNodeList, NewTempBFSKeys, FinalBFSKeys, NewTempIntersectingIntervals, FinalIntersectingIntervals), !.

get_intersecting_intervals(IntervalToIntersect, NodeList, NewNodeList, TempBFSKeys, FinalBFSKeys, TempIntersectingIntervals, FinalIntersectingIntervals):-
        %write('Here 27'),
        [Node|Ns] = NodeList,
        %write('Here 28'),
        %write('Node: '),
        %write(Node),
        red(L, K, V, R) = Node,
        %write('Here 29'),
        append(TempBFSKeys, [K], NewTempBFSKeys),
        append(NewNodeList, [L, R], NewNewNodeList),
        %write('Here 30'),
	(Lo, Hi) = IntervalToIntersect,
	(intervals_intersect((K, V), (Lo, Hi))->
           append(TempIntersectingIntervals, [(K, V)], NewTempIntersectingIntervals)
	   ;
           NewTempIntersectingIntervals = TempIntersectingIntervals
        ),
        get_intersecting_intervals(IntervalToIntersect, Ns, NewNewNodeList, NewTempBFSKeys, FinalBFSKeys, NewTempIntersectingIntervals, FinalIntersectingIntervals), !.

get_intersecting_intervals(IntervalToIntersect, NodeList, NewNodeList, TempBFSKeys, FinalBFSKeys, TempIntersectingIntervals, FinalIntersectingInterval):-
        %write('Here 31'),
        [Node|Ns] = NodeList,
        %write('Here 32'),
        %write('Node: '),
        %write(Node),
        black(L, K, V, R) = Node,
        %write('Here 33'),
        append(TempBFSKeys, [K], NewTempBFSKeys),
        append(NewNodeList, [L,R], NewNewNodeList),
        %write('Here 34'),
	(Lo, Hi) = IntervalToIntersect,
	(intervals_intersect((K, V), (Lo, Hi))->
           append(TempIntersectingIntervals, [(K, V)], NewTempIntersectingIntervals)
	   ;
           NewTempIntersectingIntervals = TempIntersectingIntervals
        ),
        get_intersecting_intervals(IntervalToIntersect, Ns, NewNewNodeList, NewTempBFSKeys, FinalBFSKeys, NewTempIntersectingIntervals, FinalIntersectingInterval), !.
	   
updateScheduledIntervalsTree( TempScheduledIntervalsTree, StartTime, FinishTime, ResourceId, NewTempScheduledIntervalsTree):-
	write('\nIn updateScheduledIntervalsTree\n'),
	(rb_lookup(ResourceId, ScheduledTaskTree, TempScheduledIntervalsTree) ->
	   write('updateScheduledIntervalsTree 1\n'),
	   rb_insert(ScheduledTaskTree, StartTime, FinishTime, NewScheduledTaskTree),
	   write('updateScheduledIntervalsTree 2\n'),
	   rb_update(TempScheduledIntervalsTree, ResourceId, NewScheduledTaskTree, NewTempScheduledIntervalsTree),
	   write('updateScheduledIntervalsTree 3\n')
	   ;
	   write('updateScheduledIntervalsTree 4\n'),
	   rb_new(ScheduledTaskTree),
	   write('updateScheduledIntervalsTree 5\n'),
	   rb_insert(ScheduledTaskTree, StartTime, FinishTime, NewScheduledTaskTree),
	   write('updateScheduledIntervalsTree 6\n'),
	   rb_insert(TempScheduledIntervalsTree, ResourceId, NewScheduledTaskTree, NewTempScheduledIntervalsTree),
	   write('updateScheduledIntervalsTree 7\n')
	), !.


schedule_tasks(UnscheduledTaskList, ErrorStream, ResourceTree, FinalScheduledTaskTree, ErrorList):-
	rb_new(TempScheduledTaskTree),
	rb_new(TempScheduledIntervalsTree),
	%write('\n Before TempScheduledIntervalsTree\n'),
	%write(TempScheduledIntervalsTree),
	%sleep(10),
	scheduleTasksOntoResources(UnscheduledTaskList, ErrorStream, ResourceTree, TempScheduledIntervalsTree, TempScheduledTaskTree, FinalScheduledTaskTree, [], ErrorList),!.


scheduleTasksOntoResources([], ErrorStream, ResourceTree, TempScheduledIntervalsTree, ScheduledTaskTree, ScheduledTaskTree, ErrorList, ErrorList):-!.

%All resource trees are 0 length
findBestResource([], ResourceTree, ScheduledTaskTree, DepTasks, EST, WcCapacityType, BestResourceId):-
	write('findBestResource empty AltWcList, failing\n'),
	%sleep(10),
	fail, !.


findBestResource([ResourceId|AltWcList], ResourceTree, ScheduledTaskTree, DepTasks, EST, WcCapacityType, BestResourceId):-
        write('In findBestResource initialization\n'),
 	%Initialize first good start
        %write('ResourceId: '),
        %write(ResourceId),
	rb_lookup(ResourceId, ResourceAvailableIntervalTree, ResourceTree),
	rb_size(ResourceAvailableIntervalTree, ResourceAvailableIntervalTreeSize),
        write('ResourceAvailableIntervalTreeSize: '),
        write(ResourceAvailableIntervalTreeSize),
	write('AltWcList: '),
	write(AltWcList),
	write('\n'),
	(ResourceAvailableIntervalTreeSize = 0 ->
           findBestResource(AltWcList, ResourceTree, ScheduledTaskTree, DepTasks, EST, WcCapacityType, BestResourceId)
           ;

           write('\n'),
           write('In findBestResource initialization 2\n'),
           findGoodStart(ResourceAvailableIntervalTree, ScheduledTaskTree, DepTasks, EST, WcCapacityType, GoodStart),
           write('In findBestResource initialization 3\n'),
	   findBestResource(AltWcList, ResourceTree, ScheduledTaskTree, DepTasks, EST, WcCapacityType, GoodStart, ResourceId, BestResourceId)
        ), !.

findBestResource([], ResourceTree, ScheduledTaskTree, DepTasks, EST, WcCapacityType, CurrentGoodStart, BestResourceId, BestResourceId):-
   	write('In findBestResource empty list\n'),!.
	

findBestResource([ResourceId|AltWcList], ResourceTree, ScheduledTaskTree, DepTasks, EST, WcCapacityType, CurrentGoodStart, TempBestResourceId, FinalBestResourceId):-
        %TODO  Analyze resources better to choose the best one from the alt wcs
	rb_lookup(ResourceId, ResourceAvailableIntervalTree, ResourceTree),
        findGoodStart(ResourceAvailableIntervalTree, ScheduledTaskTree, DepTasks, EST, WcCapacityType, GoodStart),
	(GoodStart < CurrentGoodStart ->
 	   findBestResource(AltWcList, ResourceTree, ScheduledTaskTree, DepTasks, EST, WcCapacityType, GoodStart, ResourceId, FinalBestResourceId)
           ;
 	   findBestResource(AltWcList, ResourceTree, ScheduledTaskTree, DepTasks, EST, WcCapacityType, CurrentGoodStart, TempBestResourceId, FinalBestResourceId)
        ), !.
	


scheduleTasksOntoResources([UnscheduledTask|UnscheduledTaskList], ErrorStream, ResourceTree, TempScheduledIntervalsTree, ScheduledTaskTree, FinalScheduledTaskTree, ErrorList, FinalErrorList):-!,
	write('\nIn scheduleTasksOntoResources\n'),
	%write('\nTempScheduledIntervalsTree\n'),
	%write(TempScheduledIntervalsTree),
	%sleep(10),
	unscheduled_task(Name, TaskId, ResourceId, Duration, EST, TaskNeededByTime, LiveStart, DepTasks) = UnscheduledTask,
	write('Initial EST: '),
	write(EST),
	write('\n'),
	write('TaskNeededByTime: '),
	write(TaskNeededByTime),
	write('\n'),
        wcCapacityInfo(ResourceId, WcCapacityType),
        write('Here 9.1\n\n'),
	wcAltWcList(ResourceId, AltWcList),
        write('ResourceId: '), write(ResourceId), 
        write(' has alt wcs : '), write(AltWcList), write('\n'),
        append([ResourceId], AltWcList, TotalResourceListForTask),
      	write('TotalResourceListForTask: '),
      	write(TotalResourceListForTask),
      	write('\n'),
   	(findBestResource(TotalResourceListForTask, ResourceTree, ScheduledTaskTree, DepTasks, EST, WcCapacityType, BestResourceId) ->
           true
           ;
           BestResourceId = ResourceId
        ),
	%sleep(10),
	rb_lookup(BestResourceId, ResourceAvailableIntervalTree, ResourceTree),
        write('Here 9.2\n\n'),
	rb_size(ResourceAvailableIntervalTree, ResourceAvailableIntervalTreeSize),
	write('ResourceAvailableIntervalTreeSize: '), 
	write(ResourceAvailableIntervalTreeSize), write('\n'), 
	(ResourceAvailableIntervalTreeSize > 0 ->
	   rb_min(ResourceAvailableIntervalTree, RMin, RFinish),
           write('Here 9.3\n\n'),
	   rb_max(ResourceAvailableIntervalTree, RStart, RMax),
           write('Here 9.4\n\n'),
	   write('Name: '), write(Name),
	   write('\n onto resource id: '),
	   write('Scheduling task with: \n'),
	   write('Duration: '), write(Duration),
	   write('\nEST: '), write(EST),
	   write('\n onto resource id: '),
	   write(ResourceId), 
	   write('\n capacity type: '),
	   write(WcCapacityType), 
	   write('\n LiveStart: '),
	   write(LiveStart), 
	   write('\n with available time from: '), write(RMin), write(' to '), write(RMax), write('\n'),
           %sleep(10),
	   (EST =< RMax ->  
	      scheduleTaskOntoResource(UnscheduledTask, WcCapacityType, ScheduledTaskTree, ResourceAvailableIntervalTree, ScheduledTaskIntervalTree, FinalResourceAvailableIntervalTree),
              write('Here 8.9\n\n'),
	      rb_size(ScheduledTaskIntervalTree, ScheduledTaskIntervalTreeSize),
              write('\nScheduledTaskIntervalTreeSize: '),
              write(ScheduledTaskIntervalTreeSize),
	      (ScheduledTaskIntervalTreeSize = 0 -> 
	         errorCode('CRP016', ErrorDescription016),
	         format(ErrorStream, '~a, ~w, ~w, ~w, ~w ~n',['CRP016', TaskId, 'FIXME', ResourceId, ErrorDescription016]),
	         scheduleTasksOntoResources(UnscheduledTaskList, ErrorStream, ResourceTree, TempScheduledIntervalsTree, ScheduledTaskTree, FinalScheduledTaskTree, ErrorList, FinalErrorList)
                 ;
	         rb_min(ScheduledTaskIntervalTree, StartTime, Duration1),
                 write('Here 9\n\n'),
	         rb_max(ScheduledTaskIntervalTree, StartFinishTime, FinishTime),
	         ScheduledTask = scheduled_task(Name, TaskId, StartTime, FinishTime, TaskNeededByTime, ResourceId, ScheduledTaskIntervalTree),
	         %ScheduledTask = scheduled_task(Name, TaskId, 1, 10, ResourceId),
	         write('About to rb_lookup key: '), write(ResourceId),
	         write(' on tree: '), %write(TempScheduledIntervalsTree),
	         (rb_lookup(ResourceId, ScheduledTaskTreeIntervals, TempScheduledIntervalsTree) ->
                    write('Here 9.4\n\n'),
	            t(_,Root) = ScheduledTaskTreeIntervals,
                    write('Here 10\n\n'),
	            get_intersecting_intervals((StartTime, FinishTime), [Root], [], [], AllKeys, [], IntersectionIntervalsList),
                    write('Here 11\n\n')
	            ;
                    write('Here 12\n\n'),
	            IntersectionIntervalsList = []
                 ),
	         write('\nIntersectionIntervalsList\n'),
	         write(IntersectionIntervalsList),
	         %sleep(10),
                 (WcCapacityType = 1 ->
                    write('Task has been Scheduled on Infinite Capacity Resource:\n\n'),
                    write('Name: '), write(Name),
                    write('\nStartTime: '), write(StartTime),
                    write('\nFinishTime: '), write(FinishTime), 
                    %write('\n'),
                    %sleep(1),
	            rb_insert(ScheduledTaskTree, TaskId, ScheduledTask, AppendedScheduledTaskTree),
	            scheduleTasksOntoResources(UnscheduledTaskList, ErrorStream, ResourceTree, TempScheduledIntervalsTree, AppendedScheduledTaskTree, FinalScheduledTaskTree, NewErrorList, FinalErrorList)

                 ;
	            (IntersectionIntervalsList = [] ->
                       write('Here 13\n\n'),
	               updateScheduledIntervalsTree( TempScheduledIntervalsTree, StartTime, FinishTime, ResourceId, NewTempScheduledIntervalsTree),
	               %write('\nNewTempScheduledIntervalsTree\n'),
	               %write(NewTempScheduledIntervalsTree),
	               %sleep(10),
                       write('Task has been Scheduled:\n\n'),
                       write('Name: '), write(Name),
                       write('\nStartTime: '), write(StartTime),
                       write('\nFinishTime: '), write(FinishTime), 
	               getScheduleStartTime(ScheduleStartTime),
                       %sleep(1),
	  	       (FinishTime < ScheduleStartTime ->
	                  errorCode('CRP001', ErrorDescription001),
	                  format(ErrorStream, '~a, ~w, ~w, ~w, ~w ~n',['CRP001', TaskId, 'FIXME', ResourceId, ErrorDescription001])
                          ;
                          true
                       ),
                       %write('\n'),
	               rb_insert(ScheduledTaskTree, TaskId, ScheduledTask, AppendedScheduledTaskTree),

                       %Must update ResourceList with the FinalResourceAvailableIntervalTree
                       %for the resource
	               rb_update(ResourceTree, ResourceId, FinalResourceAvailableIntervalTree, NewResourceTree), 
	               write('After rb_insert 1\n'),
	               scheduleTasksOntoResources(UnscheduledTaskList, ErrorStream, NewResourceTree, NewTempScheduledIntervalsTree, AppendedScheduledTaskTree, FinalScheduledTaskTree, ErrorList, FinalErrorList)
                       ;
                       write('Here 14\n\n'),
	               write('Task overlaps with a previously scheduled task interval, it must be moved forward and the scheduling retried\n'),
	               %sleep(10),
	               %TODO this could be made faster with a tree
	               get_max_interval_value(IntersectionIntervalsList, MaxVal),
	               write('MaxVal: '),
	               write(MaxVal),
	               NewEST is MaxVal + 1,
	               unscheduled_task(Name, TaskId, ResourceId, Duration, NewEST, TaskNeededByTime, LiveStart, DepTasks) = ModifiedESTUnscheduledTask,
	               scheduleTasksOntoResources([ModifiedESTUnscheduledTask|UnscheduledTaskList], ErrorStream, ResourceTree, TempScheduledIntervalsTree, ScheduledTaskTree, FinalScheduledTaskTree, ErrorList, FinalErrorList)
                    )
                 )
              )
	      ;
	      %get_errorStream(ErrorStream),
	      %format(ErrorStream, "Test, Test, Test~n",[]),
	      append([TaskId], ErrorList, NewErrorList),
	      errorCode('CRP016', ErrorDescription016),
	      format(ErrorStream, '~a, ~w, ~w, ~w, ~w ~n',['CRP016', TaskId, 'FIXME', ResourceId, ErrorDescription016]),
	      scheduleTasksOntoResources(UnscheduledTaskList, ErrorStream, ResourceTree, TempScheduledIntervalsTree, ScheduledTaskTree, FinalScheduledTaskTree, NewErrorList, FinalErrorList)
	   )

	      ;
	      errorCode('CRP016', ErrorDescription016),
	      format(ErrorStream, '~a, ~w, ~w, ~w, ~w ~n',['CRP016', TaskId, 'FIXME', ResourceId, ErrorDescription016]),
	      append([TaskId], ErrorList, NewErrorList),
	      scheduleTasksOntoResources(UnscheduledTaskList, ErrorStream, ResourceTree, TempScheduledIntervalsTree, ScheduledTaskTree, FinalScheduledTaskTree, NewErrorList, FinalErrorList)
	   )
, !.

get_max_interval_value([(Lo, Hi)|IntersectionList], MaxVal):-
	write('\nIn get_max_interval_value 1\n'),
	write('Hi: '),
	write(Hi),
	get_max_interval_value(IntersectionList, Hi, MaxVal), !.
	

get_max_interval_value([], MaxVal, MaxVal):-
	write(' MaxVal: '),
	write(MaxVal),
	write('\nIn get_max_interval_value 6\n'),!.

get_max_interval_value([(Lo, Hi)|IntersectionList], TempMaxVal, MaxVal):-
	write('\nIn get_max_interval_value 2\n'),
	(Hi > TempMaxVal ->
	   write('\nIn get_max_interval_value 3\n'),
	   get_max_interval_value(IntersectionList, Hi, MaxVal)
	   ;
	   write('\nIn get_max_interval_value 4\n'),
	   get_max_interval_value(IntersectionList, TempMaxVal, MaxVal)
        ), !.
	   


findGoodStart(ResourceAvailableIntervalTree, ScheduledTaskTree, DepTasks, EST, WcCapacityType, GoodStart):-
	write('\nfindGoodStart 3\n'),
	write('\nEST: '),
	write(EST),
        %(WcCapacityType = 1 ->
	%   write('\nfindGoodStart 4\n'),
	   %write('\nResourceAvailableIntervalTree: '),
	   %write(ResourceAvailableIntervalTree),
	%   rb_min(ResourceAvailableIntervalTree, PossibleGoodStart, Value),
	%   write('\nPossibleGoodStart: '),
	%   write(PossibleGoodStart),
	%   (EST > PossibleGoodStart ->
	%      findClosestIntervalKey(EST,ResourceAvailableIntervalTree, IntervalKey, InInterval),
	%      write('\nInInterval: '),
	%      write(InInterval),
	%      write('\nIntervalKey: '),
	%      write(IntervalKey),
        %      (InInterval = 0 ->
        %         GoodStart = IntervalKey
        %         ;
        %         GoodStart = EST
        %      )
        %      ;
        %         GoodStart = PossibleGoodStart
        %   )
        %;
	   (DepTasks = [] ->
	      write('\nfindGoodStart 4\n'),
	      %write('\nResourceAvailableIntervalTree: '),
	      %write(ResourceAvailableIntervalTree),
	      rb_min(ResourceAvailableIntervalTree, PossibleGoodStart, Value),
	      write('\nPossibleGoodStart: '),
	      write(PossibleGoodStart),
	      write('\nValue: '),
	      write(Value),
              %sleep(10), 
	      (EST > PossibleGoodStart ->
	         findClosestIntervalKey(EST,ResourceAvailableIntervalTree, IntervalKey, InInterval),
	         write('\nInInterval: '),
	         write(InInterval),
	         write('\nIntervalKey: '),
	         write(IntervalKey),
                 (InInterval = 0 ->
                    GoodStart = IntervalKey
                    ;
                    GoodStart = EST
                 )
                 ;
                    GoodStart = PossibleGoodStart
              )
              ;
	      write('\nfindGoodStart 5\n'),
	      write('DepTasks: '),
	      write(DepTasks),
	      write('\n'),
	      %TODO:  better handling of not finding a dependent task
	      (getMaxDepFinishTime(DepTasks, ScheduledTaskTree, MaxDepFinishTime) ->
	         true
	         ;
	         MaxDepFinishTime is 0
              ),
              write('MaxDepFinishTime: '),
              write(MaxDepFinishTime),
	      write('\nEST: '),
	      write(EST),
	      write('\nfindGoodStart 6\n'),
	      %At this point we know the max finish time, but we don't know if it
	      %is inside an available resource interval or not
	      PossibleEarliestStartTime is MaxDepFinishTime + 1,
	      (EST > PossibleEarliestStartTime ->
	          EarliestStartTime = EST
                  ;
                  EarliestStartTime = PossibleEarliestStartTime
              ),
              write('EarliestStartTime: '),
              write(EarliestStartTime),
	      findClosestIntervalKey(EarliestStartTime,ResourceAvailableIntervalTree, IntervalKey, InInterval),
	      (InInterval = 0 ->
	         GoodStart = IntervalKey
 	         ;
	         GoodStart = EarliestStartTime
	      )
           ),
        %), 
        !.
	
findClosestIntervalKey(EarliestStartTime,ResourceAvailableIntervalTree, IntervalKey, InInterval):- 
	%write('\nIn findClosestIntervalKey\n'),
	t(_,Root) = ResourceAvailableIntervalTree,
	%write('\nIn findClosestIntervalKey 1\n'),
	rb_new(TempClosestStartIntervalTree),
	%write('\nIn findClosestIntervalKey 2\n'),
	findClosestIntervalKey(EarliestStartTime, [Root], [], TempClosestStartIntervalTree, FinalClosestStartIntervalTree),
	%write('\n rb_size of FinalClosestStartIntervalTree: '),
	%rb_size(FinalClosestStartIntervalTree, FinalClosestStartIntervalTreeSize),
	%write('\n\nFinalClosestStartIntervalTree: '),
	%write(FinalClosestStartIntervalTree),
	write(FinalClosestStartIntervalTreeSize),
	write('\nIn findClosestIntervalKey 3\n'),
	(rb_lookup(0,Key,FinalClosestStartIntervalTree) ->
	   write('\nIn findClosestIntervalKey 4\n'),
	   InInterval = 1,
	   IntervalKey = Key
           ;
	   write('\nIn findClosestIntervalKey 5\n'),
	   InInterval = 0,
	   %TODO don't check if FinalClosestStartIntervalTree is zero length
           % or report error
	   (rb_min(FinalClosestStartIntervalTree, LowKey, LowValue) ->
	      write('called rb_min success\n'),
	      IntervalKey = LowValue
	      ;
              IntervalKey = EarliestStartTime
           )
	   
	), !.

findClosestIntervalKey(EST, [], [], FinalCSITree, FinalCSITree):-!.

findClosestIntervalKey(EST, [], NewNodeList, TempCSITree, FinalCSITree):-
        findClosestIntervalKey(EST, NewNodeList, [], TempCSITree, FinalCSITree),!.

%TODO:  This will search all nodes worse case, this could be improved to 
%       not append nodes to the bfs list that have no opportunity of being
%       considered

findClosestIntervalKey(EST, NodeList, NewNodeList, TempCSITree, FinalCSITree):-
        %write('\nHere 1'),
        [Node|Ns] = NodeList,
        %write('\nHere 2'),
        %write('\nNode: '),
        %write(Node),
        black(L, K, V, R) = Node,
	%write('\nProcessing: \n'),
	%write('\nK: '),
	%write(K),
	%write('\nV: '),
	%write(V),
        K = [],
        findClosestIntervalKey(EST, Ns, NewNodeList, TempCSITree, FinalCSITree), !.

findClosestIntervalKey(EST, NodeList, NewNodeList, TempCSITree, FinalCSITree):-
        %write('\nHere 3'),
        [Node|Ns] = NodeList,
        %write('\nHere 4'),
        %write('\nNode: '),
        %write(Node),
        black(L, K, V, R) = Node,
        L = [],
        R = [],
	%write('\nProcessing: \n'),
	%write('\nK: '),
	%write(K),
	%write('\nV: '),
	%write(V),
        %write('\nHere 5'),
	(interval_intersects_num((K, V), EST)->
	   rb_new(InitialNewTempCSITree),
	   rb_insert(InitialNewTempCSITree, 0, K, NewTempCSITree),
           findClosestIntervalKey(EST, [], [], NewTempCSITree, FinalCSITree)
	   ;
	   %TODO don't store negative distances
	   ESTDistanceFromIntervalStart is K - EST, 
	   (ESTDistanceFromIntervalStart >= 0 ->
	     rb_insert(TempCSITree, ESTDistanceFromIntervalStart, K, NewTempCSITree),
             findClosestIntervalKey(EST, Ns, NewNodeList, NewTempCSITree, FinalCSITree)
	     ;
             findClosestIntervalKey(EST, Ns, NewNodeList, TempCSITree, FinalCSITree)
           )
        ), !.

findClosestIntervalKey(EST, NodeList, NewNodeList, TempCSITree, FinalCSITree):-
        %write('\nHere 6'),
        [Node|Ns] = NodeList,
        %write('\nHere 7'),
        %write('\nNode: '),
        %write(Node),
        red(L, K, V, R) = Node,
        L = [],
        R = [],
	%write('\nProcessing: \n'),
	%write('\nK: '),
	%write(K),
	%write('\nV: '),
	%write(V),
        %write('\nHere 8'),
	(interval_intersects_num((K, V), EST)->
	   rb_new(InitialNewTempCSITree),
	   rb_insert(InitialNewTempCSITree, 0, K, NewTempCSITree),
           findClosestIntervalKey(EST, [], [], NewTempCSITree, FinalCSITree)
	   ;
	   ESTDistanceFromIntervalStart is K - EST, 
	   (ESTDistanceFromIntervalStart >= 0 ->
	     rb_insert(TempCSITree, ESTDistanceFromIntervalStart, K, NewTempCSITree),
             findClosestIntervalKey(EST, Ns, NewNodeList, NewTempCSITree, FinalCSITree)
	     ;
             findClosestIntervalKey(EST, Ns, NewNodeList, TempCSITree, FinalCSITree)
           )
        ), !.

findClosestIntervalKey(EST, NodeList, NewNodeList, TempCSITree, FinalCSITree):-
        %write('\nHere 11'),
        [Node|Ns] = NodeList,
        %write('\nHere 12'),
        %write('\nNode: '),
        %write(Node),
        red(L, K, V, R) = Node,
        L = [],
        %write('\nHere 13'),
        append(NewNodeList, [R], NewNewNodeList),
	%write('\nProcessing: \n'),
	%write('\nK: '),
	%write(K),
	%write('\nV: '),
	%write(V),
        %write('\nHere 14'),
	(interval_intersects_num((K, V), EST)->
	   rb_new(InitialNewTempCSITree),
	   rb_insert(InitialNewTempCSITree, 0, K, NewTempCSITree),
           findClosestIntervalKey(EST, [], [], NewTempCSITree, FinalCSITree)
	   ;
	   ESTDistanceFromIntervalStart is K - EST, 
	   (ESTDistanceFromIntervalStart >= 0 ->
	     rb_insert(TempCSITree, ESTDistanceFromIntervalStart, K, NewTempCSITree),
             findClosestIntervalKey(EST, Ns, NewNewNodeList, NewTempCSITree, FinalCSITree)
	     ;
             findClosestIntervalKey(EST, Ns, NewNewNodeList, TempCSITree, FinalCSITree)
           )
        ), !.

findClosestIntervalKey(EST, NodeList, NewNodeList, TempCSITree, FinalCSITree):-
        %write('\nHere 15'),
        [Node|Ns] = NodeList,
        %write('\nHere 16'),
        %write('\nNode: '),
        %write(Node),
        black(L, K, V, R) = Node,
        L = [],
        %write('Here 13'),
        append(NewNodeList, [R], NewNewNodeList),
	%write('\nProcessing: \n'),
	%write('\nK: '),
	%write(K),
	%write('\nV: '),
	%write(V),
        %write('Here 14'),
	(interval_intersects_num((K, V), EST)->
	   rb_new(InitialNewTempCSITree),
	   rb_insert(InitialNewTempCSITree, 0, K, NewTempCSITree),
           findClosestIntervalKey(EST, [], [], NewTempCSITree, FinalCSITree)
	   ;
	   ESTDistanceFromIntervalStart is K - EST, 
	   (ESTDistanceFromIntervalStart >= 0 ->
	     rb_insert(TempCSITree, ESTDistanceFromIntervalStart, K, NewTempCSITree),
             findClosestIntervalKey(EST, Ns, NewNewNodeList, NewTempCSITree, FinalCSITree)
	     ;
             findClosestIntervalKey(EST, Ns, NewNewNodeList, TempCSITree, FinalCSITree)
           )
        ), !.


findClosestIntervalKey(EST, NodeList, NewNodeList, TempCSITree, FinalCSITree):-
        %write('\nHere 19'),
        [Node|Ns] = NodeList,
        %write('\nHere 20'),
        %write('\nNode: '),
        %write(Node),
        red(L, K, V, R) = Node,
        R = [],
        %write('Here 21'),
        append(NewNodeList, [L], NewNewNodeList),
	%write('\nProcessing: \n'),
	%write('\nK: '),
	%write(K),
	%write('\nV: '),
	%write(V),
        %write('Here 22'),
	(interval_intersects_num((K, V), EST)->
	   rb_new(InitialNewTempCSITree),
	   rb_insert(InitialNewTempCSITree, 0, K, NewTempCSITree),
           findClosestIntervalKey(EST, [], [], NewTempCSITree, FinalCSITree)
	   ;
	   ESTDistanceFromIntervalStart is K - EST, 
	   (ESTDistanceFromIntervalStart >= 0 ->
	     rb_insert(TempCSITree, ESTDistanceFromIntervalStart, K, NewTempCSITree),
             findClosestIntervalKey(EST, Ns, NewNewNodeList, NewTempCSITree, FinalCSITree)
	     ;
             findClosestIntervalKey(EST, Ns, NewNewNodeList, TempCSITree, FinalCSITree)
           )
        ), !.

findClosestIntervalKey(EST, NodeList, NewNodeList, TempCSITree, FinalCSITree):-
        %write('\nHere 21'),
        [Node|Ns] = NodeList,
        %write('\nHere 22'),
        %write('Node: '),
        %write(Node),
        black(L, K, V, R) = Node,
        R = [],
        %write('Here 21'),
        append(NewNodeList, [L], NewNewNodeList),
	%write('\nProcessing: \n'),
	%write('\nK: '),
	%write(K),
	%write('\nV: '),
	%write(V),
        %write('Here 22'),
	(interval_intersects_num((K, V), EST)->
	   rb_new(InitialNewTempCSITree),
	   rb_insert(InitialNewTempCSITree, 0, K, NewTempCSITree),
           findClosestIntervalKey(EST, [], [], NewTempCSITree, FinalCSITree)
	   ;
	   ESTDistanceFromIntervalStart is K - EST, 
	   (ESTDistanceFromIntervalStart >= 0 ->
	     rb_insert(TempCSITree, ESTDistanceFromIntervalStart, K, NewTempCSITree),
             findClosestIntervalKey(EST, Ns, NewNewNodeList, NewTempCSITree, FinalCSITree)
	     ;
             findClosestIntervalKey(EST, Ns, NewNewNodeList, TempCSITree, FinalCSITree)
           )
        ), !.

findClosestIntervalKey(EST, NodeList, NewNodeList, TempCSITree, FinalCSITree):-
        %write('\nHere 27'),
        [Node|Ns] = NodeList,
        %write('\nHere 28'),
        %write('Node: '),
        %write(Node),
        red(L, K, V, R) = Node,
        %write('Here 29'),
        append(NewNodeList, [L, R], NewNewNodeList),
	%write('\nProcessing: \n'),
	%write('\nK: '),
	%write(K),
	%write('\nV: '),
	%write(V),
        %write('Here 30'),
	(interval_intersects_num((K, V), EST)->
	   rb_new(InitialNewTempCSITree),
	   rb_insert(InitialNewTempCSITree, 0, K, NewTempCSITree),
           findClosestIntervalKey(EST, [], [], NewTempCSITree, FinalCSITree)
	   ;
	   ESTDistanceFromIntervalStart is K - EST, 
	   (ESTDistanceFromIntervalStart >= 0 ->
	     rb_insert(TempCSITree, ESTDistanceFromIntervalStart, K, NewTempCSITree),
             findClosestIntervalKey(EST, Ns, NewNewNodeList, NewTempCSITree, FinalCSITree)
	     ;
             findClosestIntervalKey(EST, Ns, NewNewNodeList, TempCSITree, FinalCSITree)
           )
        ), !.

findClosestIntervalKey(EST, NodeList, NewNodeList, TempCSITree, FinalCSITree):-
        %write('\nHere 30'),
        [Node|Ns] = NodeList,
        %write('\nHere 31'),
        %write('Node: '),
        %write(Node),
        black(L, K, V, R) = Node,
        %write('Here 29'),
        append(NewNodeList, [L, R], NewNewNodeList),
        %write('Here 30'),
	%write('\nProcessing: \n'),
	%write('\nK: '),
	%write(K),
	%write('\nV: '),
	%write(V),
	(interval_intersects_num((K, V), EST)->
	   rb_new(InitialNewTempCSITree),
	   rb_insert(InitialNewTempCSITree, 0, K, NewTempCSITree),
           findClosestIntervalKey(EST, [], [], NewTempCSITree, FinalCSITree)
	   ;
	   ESTDistanceFromIntervalStart is K - EST, 
	   (ESTDistanceFromIntervalStart >= 0 ->
	     rb_insert(TempCSITree, ESTDistanceFromIntervalStart, K, NewTempCSITree),
             findClosestIntervalKey(EST, Ns, NewNewNodeList, NewTempCSITree, FinalCSITree)
	     ;
             findClosestIntervalKey(EST, Ns, NewNewNodeList, TempCSITree, FinalCSITree)
           )
        ), !.

getMaxDepFinishTime([TaskId | DepTasks], ScheduledTaskTree, MaxDepFinishTime):-
	write('\n In getMaxDepFinishTime\n'),
	write('TaskId: '),
	write(TaskId),
	write('\n'),
	%write('ScheduledTaskTree: '),
	%write(ScheduledTaskTree),
	write('\n'),
	rb_lookup(TaskId, ScheduledTask, ScheduledTaskTree),
	write('\n In getMaxDepFinishTime 2\n'),
	scheduled_task(Name, TaskId, StartTime, FinishTime, TaskNeededByTime, ResourceId, ScheduledTaskIntervalTree) = ScheduledTask,
	getMaxDepFinishTime(DepTasks, ScheduledTaskTree, FinishTime, MaxDepFinishTime), !.
	 
getMaxDepFinishTime([], ScheduledTaskTree, MaxDepFinishTime, MaxDepFinishTime):-!.

getMaxDepFinishTime([TaskId|DepTasks], ScheduledTaskTree, TempMaxDepFinishTime, MaxDepFinishTime):-
	write('\n In getMaxDepFinishTime 2\n'),
	rb_lookup(TaskId, ScheduledTask, ScheduledTaskTree),
	scheduled_task(Name, TaskId, StartTime, FinishTime, TaskNeededByTime, ResourceId, ScheduledTaskIntervalTree) = ScheduledTask,
	(TempMaxDepFinishTime < FinishTime ->
	   getMaxDepFinishTime(DepTasks, ScheduledTaskTree, FinishTime, MaxDepFinishTime)
           ;
	   getMaxDepFinishTime(DepTasks, ScheduledTaskTree, TempMaxDepFinishTime, MaxDepFinishTime)
	), !.
	


scheduleTaskOntoResource(UnscheduledTask, WcCapacityType, ScheduledTaskTree, ResourceAvailableIntervalTree, FinalScheduledTaskIntervalTree, FinalResourceAvailableIntervalTree):- !,
	write('In scheduleTaskOntoResource 1\n'),
	unscheduled_task(_, _, _, Duration, EST, TaskNeededByTime, LiveStart, DepTasks) = UnscheduledTask,
        (LiveStart = 1 ->
           GoodStart is EST
           ;
	   getScheduleStartTime(ScheduleStartTime),
	   (EST < ScheduleStartTime ->
	      findGoodStart(ResourceAvailableIntervalTree, ScheduledTaskTree, DepTasks, ScheduleStartTime, WcCapacityType, GoodStart)
              ;
	      findGoodStart(ResourceAvailableIntervalTree, ScheduledTaskTree, DepTasks, EST, WcCapacityType, GoodStart)
        )
        ),
	write('GoodStart: '),
	write(GoodStart),
	write('\n'),
	%rb_min(ResourceAvailableIntervalTree, Start, Value),
	TaskToBeScheduled = (GoodStart, Duration),
	rb_new(ScheduledTaskIntervalTree),
	write('In scheduleTaskOntoResource 2\n'),
	
	scheduleTaskOntoResource(TaskToBeScheduled, WcCapacityType, ScheduledTaskTree, ResourceAvailableIntervalTree, ScheduledTaskIntervalTree, FinalScheduledTaskIntervalTree, FinalResourceAvailableIntervalTree).


%TODO:  create a way to pass back if any duration was remaining
scheduleTaskOntoResource(TaskToBeScheduled, WcCapacityType, ScheduledTaskTree, ResourceAvailableIntervalTree, ScheduledTaskIntervalTree, FinalScheduledTaskIntervalTree, FinalResourceAvailableIntervalTree):- !,
	write('In scheduleTaskOntoResource 2\n'),
	(Lo, Hi) = TaskToBeScheduled,
	%write('ResourceAvailableIntervalTree: '),
	%write(ResourceAvailableIntervalTree),
	write('\nLo: '),
	write(Lo),
	write('\nHi: '),
	write(Hi),
	NewHi is Lo + Hi,
	write('\nNewHi: '),
	write(NewHi),
	write('\n'),
       	%sleep(10),
	%intersect_interval(ResourceAvailableIntervalTree, Lo, NewHi, L),
	%sleep(5),
	t(_,Root) = ResourceAvailableIntervalTree,
	get_intersecting_intervals((Lo, NewHi), [Root], [], [], AllKeys, [], L),
        %TODO  L is not sorted so doesn't work right put it into a tree
	write('L: '),
	%write(L),
       	%sleep(10),
	(intersectTaskToBeScheduledWithIntersectingIntervals(TaskToBeScheduled, 
                                             L,ResourceAvailableIntervalTree, 
                                               AdjustedResourceAvailableIntervalTree,
                                               ScheduledTaskIntervalTree,
					          NewScheduledTaskIntervalTree,
                                                  RemainingDuration) ->
	   write('Success with intersectTaskToBeScheduledWithIntersectingIntervals\n')
           ;
	   write('Failure with intersectTaskToBeScheduledWithIntersectingIntervals\n')
	   %sleep(10)
        ),
           
        write('Here 4\n\n'),
        write('Remaining Duration\n'),
        write(RemainingDuration),
	(RemainingDuration =< 0 ->
        write('Here 5\n\n'),
	   FinalScheduledTaskIntervalTree =  NewScheduledTaskIntervalTree, 
	   FinalResourceAvailableIntervalTree = AdjustedResourceAvailableIntervalTree,
        write('Here 7\n\n')
	   ;
        write('Here 6\n\n'),
	   rb_max(NewScheduledTaskIntervalTree, _,  MaxFinishTime),
           write('MaxFinishTime: '),
           write(MaxFinishTime),
           Start is MaxFinishTime + 1,
	   rb_size(AdjustedResourceAvailableIntervalTree, AdjustedResourceAvailableIntervalTreeSize),
	   write('AdjustedResourceAvailableIntervalTreeSize: '),
	   write(AdjustedResourceAvailableIntervalTreeSize),
	   write('\n'),
	   (AdjustedResourceAvailableIntervalTreeSize = 0 ->
	      FinalScheduledTaskIntervalTree =  NewScheduledTaskIntervalTree, 
	      FinalResourceAvailableIntervalTree = AdjustedResourceAvailableIntervalTree
              ;
	      findGoodStart(AdjustedResourceAvailableIntervalTree, ScheduledTaskTree, [], Start, WcCapacityType, GoodStart),
	      write('\nGoodStart: '),
	      write(GoodStart),
	      scheduleTaskOntoResource((GoodStart, RemainingDuration), WcCapacityType, 
					     TaskScheduledTree,
		                     AdjustedResourceAvailableIntervalTree, 
			             NewScheduledTaskIntervalTree, 
				     FinalScheduledTaskIntervalTree, 
				     FinalResourceAvailableIntervalTree)
           )
        ).

getTotalIntersectionTime(IntervalIntersectionList, TotalIntersectionTime):-!,
	getTotalIntersectionTime(IntervalIntersectionList,0, TotalIntersectionTime).

getTotalIntersectionTime([], T, T):- !.

getTotalIntersectionTime([Interval, IntersectionList], Temp, T):- !,
	interval_length(Interval,IL),
	NewTempTime is Temp + IL, 
	getTotalIntersectionTime(IntersectionList, NewTempTime, T).

convertIntersectingIntervalsListToRbTree([], FinalTree, FinalTree):-!.
       	%write('In convertIntersectingIntervalsListToRbTree\n'),!.

convertIntersectingIntervalsListToRbTree([L|Ls], TempTree, FinalTree):-
       	%write('In convertIntersectingIntervalsListToRbTree\n'),
        (IntLow, IntHigh) = L,
	rb_insert(TempTree, IntLow, IntHigh, NewTree),
	convertIntersectingIntervalsListToRbTree(Ls, NewTree, FinalTree),!.

intersectTaskToBeScheduledWithIntersectingIntervals(TaskToBeScheduled, L, ResourceAvailableIntervalTree,                                                NewResourceAvailableIntervalTree, 
                                                    Tree, FinalTree, 
                                                    RemainingDuration):- !,
        write('\nIn intersectTaskToBeScheduledWithIntersectingIntervals 3\n'),
	rb_new(TempIntersectingIntervalTree),
	%rb_new(IntersectingIntervalTree),
	convertIntersectingIntervalsListToRbTree(L, TempIntersectingIntervalTree, IntersectingIntervalTree),
	intersectTaskToBeScheduledWithIntersectingIntervals(TaskToBeScheduled, 
                                                            IntersectingIntervalTree, ResourceAvailableIntervalTree,                                                NewResourceAvailableIntervalTree, Tree, 
                                                            FinalTree, 
                                                            -1, 
                                                            RemainingDuration).
	
intersectTaskToBeScheduledWithIntersectingIntervals(TaskToBeScheduled, IntersectingIntervalTree, T1, T1, T, T, Rd, Rd):- 
	rb_empty(IntersectingIntervalTree),
        write('\nintersectTaskToBeScheduledWithIntersectingIntervals test for empty success\n'),
	rb_size(T1, T1Size),
	write('T1 size: '),
	write(T1Size),
	write('\n'),
	rb_size(T, TSize),
	write('T size: '),
	write(TSize),
	write('\n'),
	write('Remaining Duration: '),
	write(Rd),
	write('\n'),
	%sleep(10),
          !.
%	(Start, _) = Task,
%	interval_length(Task, Rd), !.

intersectTaskToBeScheduledWithIntersectingIntervals(_, _, T1, T1,  T, T, 0, 0):- 
%write('Here 2 \n\n'),
        %write('\nIn intersectTaskToBeScheduledWithIntersectingIntervals 3\n'),
!.
%	(Start, _) = Task,
%	interval_length(Task, Rd), !.

intersectTaskToBeScheduledWithIntersectingIntervals(TaskToBeScheduled, 
                                                            IntersectingIntervalTree, 
                                                ResourceAvailableIntervalTree,                                                NewResourceAvailableIntervalTree,
                                                            Tree, 
                                                            FinalTree, 
                                                            RemainingDuration, 
                                                            FinalRD):-
        write('\nIn intersectTaskToBeScheduledWithIntersectingIntervals\n'),
        write('RemainingDuration: '),
        write(RemainingDuration),
        write('\n'),
	rb_size(IntersectingIntervalTree, IntersectingIntervalTreeSize),
	write('IntersectingIntervalTreeSize: '),
	write(IntersectingIntervalTreeSize),
	(IntersectingIntervalTreeSize = 0 ->
	   write('IntersectingIntervalTreeSize is 0'),
	   (rb_empty(IntersectingIntervalTree) ->
	      write('IntersectingIntervalTree is empty')
	      ;
	      write('IntersectingIntervalTree is not empty')
           )
	   %sleep(10)
           ;
           true
        ),
	   
	write('\n'),
        rb_del_min(IntersectingIntervalTree, ResourceLow, ResourceHigh, DeletedIntersectingIntervalTree),
	(ResourceLow, ResourceHigh) = L,
	(ResourceHigh < ResourceLow ->
	   errorCode('CRP017', ErrorDescription017),
	   format(ErrorStream, '~a, ~w, ~w, ~w, ~w ~n',['CRP017', 'FIXME', 'FIXME', 'FIXME', ErrorDescription017]),
	   errorCode('CRP018', ErrorDescription018),
	   format(ErrorStream, '~a, ~w, ~w, ~w, ~w ~n',['CRP018', 'FIXME', 'FIXME', 'FIXME','FIXME', ErrorDescription018]),
	   halt('CRP018')
	   ;
           true
        ),
	write('ResourceLow: '),
	write(ResourceLow),
	write(' ResourceHigh: '),
	write( ResourceHigh),
	write('\n'),
        (UnscheduledTaskLow, UnscheduledTaskDuration) = TaskToBeScheduled,
	write('UnscheduledTaskLow: '),
	write(UnscheduledTaskLow),
	write(' UnscheduledTaskDuration: '),
	write(UnscheduledTaskDuration),
	write('\n'),
	ResourceLow =< UnscheduledTaskLow,	
	write('About to Intersect: \n'),
	write('TaskToBeScheduled: \n'),
	write(TaskToBeScheduled),
	write('\n and L: \n'),
	write(L),
	UnscheduledTaskHigh is UnscheduledTaskLow + UnscheduledTaskDuration,
	UnscheduledTaskInterval = (UnscheduledTaskLow, UnscheduledTaskHigh),
	write('\nUnscheduledTaskInterval: '),
	write(UnscheduledTaskInterval),
	interval_intersection(UnscheduledTaskInterval, L, Intersection),
	write('\nIntersection: \n'),
	write(Intersection),
        (IntLow, IntHigh) = Intersection,
	%TODO remove/adjust time intervals in the available time set for the
        %resource
	rb_insert(Tree, IntLow, IntHigh, NewTree),
	adjustResourceAvailableIntervalTree(ResourceAvailableIntervalTree, L, Intersection, AdjustedResourceAvailableIntervalTree),
	%rb_delete(ResourceAvailableIntervalTree, ResourceLow, ResourceHigh, AdjustedResourceAvailableIntervalTree),
	interval_length(Intersection, IntersectionLength),
	write('\nIntersectionLength: \n'),
	write(IntersectionLength),
	%interval_length(TaskToBeScheduled, TaskToBeScheduledLength),
	interval_length(UnscheduledTaskInterval, TaskToBeScheduledLength),
	NewRemainingDuration is TaskToBeScheduledLength - IntersectionLength,
	write('NewRemainingDuration: \n'),
	write(NewRemainingDuration),
	(NewRemainingDuration > 0 ->
	   get_remaindertask(UnscheduledTaskInterval, Intersection, 
                          %IntersectionLength, 
                          NewRemainingDuration,
                          RemainderTaskToBeScheduled),
	   intersectTaskToBeScheduledWithIntersectingIntervals(
                                                    RemainderTaskToBeScheduled, 
                                                    DeletedIntersectingIntervalTree, 
				    AdjustedResourceAvailableIntervalTree,
	                                   NewResourceAvailableIntervalTree,

                                                            NewTree, 
                                                            FinalTree, 
                                                           NewRemainingDuration,
                                                            FinalRD)
          
           ;
	   intersectTaskToBeScheduledWithIntersectingIntervals(
                                                    TaskToBeScheduled, 
                                                            DeletedIntersectingIntervalTree, 
				    AdjustedResourceAvailableIntervalTree,
                                           NewResourceAvailableIntervalTree,
                                                            NewTree, 
                                                            FinalTree, 
                                                           NewRemainingDuration,
                                                            FinalRD)
        ),!.
	
intersectTaskToBeScheduledWithIntersectingIntervals(TaskToBeScheduled, 
                                                            IntersectingIntervalTree, 
                                                ResourceAvailableIntervalTree,                                                NewResourceAvailableIntervalTree,
                                                            Tree, 
                                                            FinalTree, 
                                                            RemainingDuration, 
                                                            FinalRD):-
        write('\nIn intersectTaskToBeScheduledWithIntersectingIntervals >\n'),
        rb_del_min(IntersectingIntervalTree, ResourceLow, ResourceHigh, DeletedIntersectingIntervalTree),
	%(ResourceLow, ResoureHigh) = L,
        (UnscheduledTaskLow, UnscheduledTaskDuration) = TaskToBeScheduled,
	write('ResourceLow: '),
	write(ResourceLow),
	write(' ResourceHigh: '),
	write( ResourceHigh),
	write('\n'),
        (UnscheduledTaskLow, UnscheduledTaskDuration) = TaskToBeScheduled,
	write('UnscheduledTaskLow: '),
	write(UnscheduledTaskLow),
	write(' UnscheduledTaskDuration: '),
	write(UnscheduledTaskDuration),
	write('\n'),
	NewTaskToBeScheduled = (ResourceLow, UnscheduledTaskDuration),
	intersectTaskToBeScheduledWithIntersectingIntervals(NewTaskToBeScheduled, 
                                                            IntersectingIntervalTree, 
                                                            %DeletedIntersectingIntervalTree, 
                                                ResourceAvailableIntervalTree,                                                NewResourceAvailableIntervalTree,
                                                            Tree, 
                                                            FinalTree, 
                                                            RemainingDuration, 
                                                            FinalRD),!.

%  ----
%  ----
	
adjustResourceAvailableIntervalTree(ResourceAvailableIntervalTree, L, Intersection, AdjustedResourceAvailableIntervalTree):-
	write('\nIn adjustResourceAvailableIntervalTree test ='),
	write('\nL: '),
	write(L),
	write('\n'),
	write('\nIntersection: '),
	write(Intersection),
	write('\n'),
	(ResourceLow, ResourceHigh) = L,
        (IntLow, IntHigh) = Intersection,
	IntLow = ResourceLow,
	IntHigh = ResourceHigh,
	rb_delete(ResourceAvailableIntervalTree, ResourceLow, ResourceHigh, AdjustedResourceAvailableIntervalTree),!.


%R  ----
%I    ----
	
adjustResourceAvailableIntervalTree(ResourceAvailableIntervalTree, L, Intersection, AdjustedResourceAvailableIntervalTree):-
	write('\nIn adjustResourceAvailableIntervalTree test I >'),
	write('\nL: '),
	write(L),
	write('\n'),
	write('\nIntersection: '),
	write(Intersection),
	write('\n'),
	%write('\nResourceAvailableIntervalTree: '),
	%write(ResourceAvailableIntervalTree),
	write('\n'),
	(ResourceLow, ResourceHigh) = L,
	write('\nHere test I > 5 '),
        (IntLow, IntHigh) = Intersection,
	write('\nHere test I > 4 '),
	IntLow > ResourceLow,
	write('\nHere test I > 3 '),
	write('\nIntHigh '),
	write(IntHigh),
	write('\n'),
	write('\nResourceHigh: '),
	write(ResourceHigh),
	write('\n'),
	IntHigh > ResourceHigh,
	write('\nHere test I > 2 '),
	NewResourceHigh is IntLow - 1,
	write('\nHere test I > 1 '),
	rb_update(ResourceAvailableIntervalTree, ResourceLow, NewResourceHigh, AdjustedResourceAvailableIntervalTree),!.
	
%R  -------
%I  ----
	
adjustResourceAvailableIntervalTree(ResourceAvailableIntervalTree, L, Intersection, AdjustedResourceAvailableIntervalTree):-
	write('\nIn adjustResourceAvailableIntervalTree test R >'),
	(ResourceLow, ResourceHigh) = L,
	write('\nIn adjustResourceAvailableIntervalTree test R > 4'),
        (IntLow, IntHigh) = Intersection,
	write('\nIn adjustResourceAvailableIntervalTree test R > 5'),
	IntLow = ResourceLow,
	write('\nIn adjustResourceAvailableIntervalTree test R > 6'),
	write('\nIntHigh: \n'),
	write(IntHigh),
	write('\nResourceHigh: \n'),
	write(ResourceHigh),
	IntHigh < ResourceHigh,
	write('\nIn adjustResourceAvailableIntervalTree test R > 7'),
	NewResourceLow is IntHigh + 1,
	write('\nIn adjustResourceAvailableIntervalTree test R > 1'),
	rb_delete(ResourceAvailableIntervalTree, ResourceLow, ResourceHigh, DeletedResourceAvailableIntervalTree),
	write('\nIn adjustResourceAvailableIntervalTree test R > 2'),
	rb_insert(DeletedResourceAvailableIntervalTree, NewResourceLow, ResourceHigh, AdjustedResourceAvailableIntervalTree),
	write('\nIn adjustResourceAvailableIntervalTree test R > 3'),

	!.

%R  -------
%I     ----
	
adjustResourceAvailableIntervalTree(ResourceAvailableIntervalTree, L, Intersection, AdjustedResourceAvailableIntervalTree):-
	write('\nIn adjustResourceAvailableIntervalTree test R <'),
	(ResourceLow, ResourceHigh) = L,
	write('\nIn adjustResourceAvailableIntervalTree test R < 4'),
        (IntLow, IntHigh) = Intersection,
	write('\nIn adjustResourceAvailableIntervalTree test R < 5'),
	IntLow > ResourceLow,
	write('\nIn adjustResourceAvailableIntervalTree test R < 6'),
	write('\nIntHigh: \n'),
	write(IntHigh),
	write('\nResourceHigh: \n'),
	write(ResourceHigh),
	IntHigh = ResourceHigh,
	write('\nIn adjustResourceAvailableIntervalTree test R < 7'),
	NewResourceHigh is IntLow - 1,
	write('\nIn adjustResourceAvailableIntervalTree test R < 1'),
	write('\nIn adjustResourceAvailableIntervalTree test R < 2'),
	rb_update(ResourceAvailableIntervalTree, ResourceLow, NewResourceHigh, AdjustedResourceAvailableIntervalTree),
	write('\nIn adjustResourceAvailableIntervalTree test R < 3'),

	!.

%R  -------
%I    ----
	
adjustResourceAvailableIntervalTree(ResourceAvailableIntervalTree, L, Intersection, AdjustedResourceAvailableIntervalTree):-
	write('\nIn adjustResourceAvailableIntervalTree test I in R 1'),
	(ResourceLow, ResourceHigh) = L,
	write('\nIn adjustResourceAvailableIntervalTree test I in R 2'),
        (IntLow, IntHigh) = Intersection,
	write('\nIn adjustResourceAvailableIntervalTree test I in R 3'),
	IntLow > ResourceLow,
	write('\nIn adjustResourceAvailableIntervalTree test I in R 4'),
	write('\nIntHigh: \n'),
	write(IntHigh),
	write('\nResourceHigh: \n'),
	write(ResourceHigh),
	IntHigh < ResourceHigh,
	write('\nIn adjustResourceAvailableIntervalTree test I in R 5'),
	NewResourceHigh is IntLow - 1,
	write('\nIn adjustResourceAvailableIntervalTree test I in R 6'),
	rb_update(ResourceAvailableIntervalTree, ResourceLow, NewResourceHigh, UpdatedAdjustedResourceAvailableIntervalTree),
	NewResourceLow is IntHigh + 1,
	rb_insert(UpdatedAdjustedResourceAvailableIntervalTree, NewResourceLow, ResourceHigh, AdjustedResourceAvailableIntervalTree),
	write('\nIn adjustResourceAvailableIntervalTree test I in R 7'),
	!.

createBenchmarkTasks(NumTasks, Duration, UnscheduledTaskList):-
	createBenchmarkTasks(0, NumTasks, Duration, [], UnscheduledTaskList), !.
	

createBenchmarkTasks(T, NumTasks, Duration, L, L2):- !,
	TaskNum is T + 1,
	atom_concat('Task ', TaskNum, TaskName),
	Task = unscheduled_task(TaskName, TaskNum, 1, Duration, 0, []),
	append(L, [Task], NewL),
	(TaskNum = NumTasks ->
           L2 = NewL
           ;
	   createBenchmarkTasks(TaskNum, NumTasks, Duration, NewL, L2)
        ).

simpleScheduleTasks(UnscheduledTaskList, ScheduledTasks):- !,
	simpleScheduleTasks(UnscheduledTaskList, [], ScheduledTasks).

simpleScheduleTasks([], ScheduledTasks, ScheduledTasks):-!.
	
simpleScheduleTasks([UnScheduledTask|UnscheduledTaskList], TempScheduledTaskList, ScheduledTasks):- !,
	unscheduled_task(Name, TaskId, ResourceId, Duration, 0, []) = UnScheduledTask,
	ScheduledTask = scheduled_task(Name, TaskId, 1, 10, ResourceId),
	append(TempScheduledTaskList, [ScheduledTask], AppendedScheduledTaskList),
        write('Task '),write(TaskId), write(' scheduled\n'),
	simpleScheduleTasks(UnscheduledTaskList, AppendedScheduledTaskList, ScheduledTasks).

verifyResourcesExist([], ErrorStream, ResourceTree):-!.

verifyResourcesExist([ResourceId|AltWcList], ErrorStream, ResourceTree):-
        write('In verifyResourcesExist initialization\n'),
 	%Initialize first good start
        %write('ResourceId: '),
        %write(ResourceId),
	(rb_lookup(ResourceId, ResourceAvailableIntervalTree, ResourceTree) ->
	   true
	   ;
	   errorCode('CRP011', ErrorDescription011),
	   format(ErrorStream, '~a, ~w, ~w, ~w, ~w ~n',['CRP011', 'FIXME', 'FIXME', ResourceId, ErrorDescription011])
        ),
        %write('ResourceAvailableIntervalTree: '),
        %write(ResourceAvailableIntervalTree),
	findBestResource(AltWcList, ResourceTree), !.

        
createAdjustedSubTaskList(TaskNum, ErrorStream, TaskNeededByTime, ResourceTree, TaskEST, SubTaskList, AdjustedSubTaskList):-!,
	createAdjustedSubTaskList(TaskNum, ErrorStream, TaskNeededByTime, ResourceTree, TaskEST, SubTaskList, [], AdjustedSubTaskList).

createAdjustedSubTaskList(TaskNum, ErrorStream, TaskNeededByTime, ResourceTree, TaskEST, [], AdjustedSubTaskList, AdjustedSubTaskList):-!.

createAdjustedSubTaskList(MfgTaskNum, ErrorStream, TaskNeededByTime, ResourceTree, TaskEST, [SubTask|SubTasks], TempAdjustedSubTaskList, AdjustedSubTaskList):-!,
	%write('In createAdjustedSubTaskList\n'),
	%write('MfgTaskNum: '),
	%write(MfgTaskNum),
	subtask(TaskNum, OpNum, SetupTime, CycleTime, ResourceId, EST, OpsQty, IPQty, LiveStart, StartDate, StartTime, DepTasks) = SubTask,
	%write('In createAdjustedSubTaskList 1\n'),
        TotalQuantity is OpsQty - IPQty,
	Duration is SetupTime + TotalQuantity*CycleTime,
	SetupCycleTimeSum is SetupTime + CycleTime,
        RealStartTime is StartDate + StartTime,
        (LiveStart = 0 ->
           RealEST is TaskEST 
           ;
           RealEST is RealStartTime
        ),
	(SetupCycleTimeSum = 0 ->
	   errorCode('CRP007', ErrorDescription007),
	   write('CRP007 error \n'),
	   format(ErrorStream, '~a, ~w, ~w, ~w, ~w ~n',['CRP007', MfgTaskNum, OpNum, ResourceId, ErrorDescription007]),
	   AppendSubTask = 0
	   ;
           true
        ),
	(TotalQuantity < 0 ->
	   errorCode('CRP012', ErrorDescription012),
	   format(ErrorStream, '~a, ~w, ~w, ~w, ~w ~n',['CRP012', MfgTaskNum, OpNum, ResourceId, ErrorDescription012]),
	   (var(AppendSubTask) ->
	      AppendSubTask = 0
              ;
              true
           )
	   ;
           true
        ),
	(TotalQuantity = 0 ->
	   errorCode('CRP014', ErrorDescription014),
	   format(ErrorStream, '~a, ~w, ~w, ~w, ~w ~n',['CRP014', MfgTaskNum, OpNum, ResourceId, ErrorDescription014]),
	   (var(AppendSubTask) ->
	      AppendSubTask = 0
              ;
              true
           )
	   ;
           true
        ),
	(Duration =< 0 ->
	   errorCode('CRP013', ErrorDescription013),
	   format(ErrorStream, '~a, ~w, ~w, ~w, ~w ~n',['CRP013', MfgTaskNum, OpNum, ResourceId, ErrorDescription013]),
	   (var(AppendSubTask) ->
	      AppendSubTask = 0
              ;
              true
           )
	   ;
           true
        ),
	(var(AppendSubTask) ->
	   UnScheduledTask =  unscheduled_task(TaskNum, TaskNum, ResourceId, Duration, RealEST, TaskNeededByTime, LiveStart, DepTasks), 
	   append(TempAdjustedSubTaskList, [UnScheduledTask],  NewTempAdjustedSubTaskList),
	   createAdjustedSubTaskList(MfgTaskNum, ErrorStream, TaskNeededByTime, ResourceTree, TaskEST, SubTasks, NewTempAdjustedSubTaskList, AdjustedSubTaskList)
  	   ;
	   createAdjustedSubTaskList(MfgTaskNum, ErrorStream, TaskNeededByTime, ResourceTree, TaskEST, SubTasks, TempAdjustedSubTaskList, AdjustedSubTaskList)
        ), !.

createUnscheduledTaskList(TaskData, ErrorStream, ResourceTree, UnscheduledTaskList):-!,
	createUnscheduledTaskList(TaskData, ErrorStream, ResourceTree, [], UnscheduledTaskList).
	
createUnscheduledTaskList([], ErrorStream, ResourceTree, UnscheduledTaskList, UnscheduledTaskList):-!.

createUnscheduledTaskList([Task|Tasks], ErrorStream, ResourceTree, TempUnscheduledTaskList, UnscheduledTaskList):-!,
	task(TaskNum, TaskEarliestStartTime, TaskNeededByTime, TaskStartQuantity, SubTaskList) = Task,
	createAdjustedSubTaskList(TaskNum, ErrorStream, TaskNeededByTime, ResourceTree, TaskEarliestStartTime, SubTaskList, AdjustedSubTaskList),
	%write('After createdAdjustedSubTaskList'),
	%sleep(10),
	append( TempUnscheduledTaskList, AdjustedSubTaskList, NewTempUnscheduledTaskList),
	createUnscheduledTaskList(Tasks, ErrorStream, ResourceTree, NewTempUnscheduledTaskList, UnscheduledTaskList).
	
createWcAvailableTimeTree(WcAvailableTimeList, WcAvailableTimeTree):-!,
        rb_new(TempWcAvailableTimeTree),
	createWcAvailableTimeTree(WcAvailableTimeList, TempWcAvailableTimeTree, WcAvailableTimeTree).

createWcAvailableTimeTree([], WcAvailableTimeTree, WcAvailableTimeTree):-!.

createWcAvailableTimeTree([(Low, High)|WcAvailTimes], TempWcAvailableTimeTree, WcAvailableTimeTree):-!,
	rb_insert(TempWcAvailableTimeTree, Low, High, NewTempWcAvailableTimeTree),
	createWcAvailableTimeTree(WcAvailTimes, NewTempWcAvailableTimeTree, WcAvailableTimeTree).
	

createResourceTree(WcData, ResourceTree):-!,
	rb_new(T),
	createResourceTree(WcData, T, ResourceTree).
	
createResourceTree([], ResourceTree, ResourceTree):-!.

createResourceTree([Wc|Wcs], TempResourceTree, ResourceTree):-!,
	workcenterInfo(WcNum, IsInfiniteCapacity,  AltWcList, WcAvailableTimeList) = Wc,
	assert(wcCapacityInfo(WcNum, IsInfiniteCapacity)),
	assert(wcAltWcList(WcNum, AltWcList)),
	createWcAvailableTimeTree(WcAvailableTimeList, WcAvailableTimeTree),
        Resource = resource(WcNum, WcNum, IsInfiniteCapacity, WcAvailableTimeTree),
	rb_insert(TempResourceTree, WcNum, WcAvailableTimeTree, NewTempResourceTree),
        %append([Resource], TempResourceList, NewTempResourceList),

	createResourceTree(Wcs, NewTempResourceTree, ResourceTree).
	
outputResourceList([]):-!.

outputResourceList([Resource|Resources]):-!,
        Resource = resource(WcNum, WcNum, WcAvailableTimeTree),
	write('WcNum: '), write(WcNum),  write('\n'),

	outputResourceList(Resources).

write_scheduledsubtasks(Name, TaskId, ResourceId, TaskNeededByTime, ScheduledTaskIntervalTree):-
	rb_empty(ScheduledTaskIntervalTree), !.

write_scheduledsubtasks(Name, TaskId, ResourceId, TaskNeededByTime, ScheduledTaskIntervalTree):-
	rb_del_min(ScheduledTaskIntervalTree, StartTime, FinishTime, NewScheduledTaskIntervalTree),
	write(Name), write(', '), 
	write(TaskId), write(', '), 
	write(StartTime), write(', '), 
	write(FinishTime), write(', '), 
	write(ResourceId), write(', '),
	write(TaskNeededByTime), write('\n'),
	write_scheduledsubtasks(Name, TaskId, ResourceId, TaskNeededByTime, NewScheduledTaskIntervalTree), !.
	

write_scheduledtasks(ScheduledTaskTree, FileOutput):-
	rb_empty(ScheduledTaskTree),
	close(FileOutput), !.

write_scheduledtasks(ScheduledTaskTree, FileOutput):-
	rb_del_min(ScheduledTaskTree, Key, ScheduledTask, NewScheduledTaskTree),
	scheduled_task(Name, TaskId, StartTime, FinishTime, TaskNeededByTime, ResourceId, ScheduledTaskIntervalTree) = ScheduledTask,
	%(TaskId = 3 ->
	%   write('ScheduledTaskIntervalTree for TaskId 3: '),
	%   write(ScheduledTaskIntervalTree),
	%   sleep(10),
	%   write('\n')
        %   ;
        %   true
        %),
	write_scheduledsubtasks(Name, TaskId, ResourceId, TaskNeededByTime, ScheduledTaskIntervalTree),
	%write(Name), write(', '), 
	%write(TaskId), write(', '), 
	%write(StartTime), write(', '), 
	%write(FinishTime), write(', '), 
	%write(ResourceId), write('\n'),
	write_scheduledtasks(NewScheduledTaskTree, FileOutput), !.

write_scheduledtasks(ScheduledTaskTree):-
	open('scheduled_tasks.txt','write',FileOutput),
	set_output(FileOutput),
	write('Name, TaskId, StartTime, FinishTime, ResourceId, NeededByTime\n'),
	write_scheduledtasks(ScheduledTaskTree, FileOutput).

write_scheduling_errors([], FileOutput):-
	close(FileOutput), !.

write_scheduling_errors([Error|Errors], FileOutput):-
	write(Error), write(',\n'),
	write_scheduling_errors(Errors, FileOutput),!.

write_scheduling_errors(Errors):-
	open('scheduling_errors.txt','write',FileOutput),
	set_output(FileOutput),
	write_scheduling_errors(Errors, FileOutput),!.

 	
initializeErrorCodes(Status):-
	assert(errorCode('CRP001', 'Started operation should have ended before schedule start but no complete transaction entered')),
	assert(errorCode('CRP002', 'Later operation has a start date but previous operation has no start date')),
 	%Not done here but in the available times generator
	%assert(errorCode('CRP003', 'Work Center has no online date')),
 	%Not done here but in the available times generator
	%assert(errorCode('CRP004', 'Error in work center downtime data')),
 	%Not done here but in the available times generator
	%assert(errorCode('CRP005', 'Work center available time schedule data error')),
	assert(errorCode('CRP006', 'Operation trying to use a workcenter not entered in the route')),
	assert(errorCode('CRP007', 'Invalid Setup or Cycle time entered in route for operation')),
	assert(errorCode('CRP008', 'Manufacturing order has no route information')),
	assert(errorCode('CRP009', 'CRP abnormally terminated')),
	assert(errorCode('CRP010', 'Manufacturing operation not found in route')),
	assert(errorCode('CRP011', 'Workcenter specified does not exist to be scheduled on')),
	assert(errorCode('CRP012', 'Operation posted transactions accumulated amount greater than required amount')),
	assert(errorCode('CRP013', 'Operation posted transactions occurred before a start date was specified')),
	assert(errorCode('CRP014', 'Operation quantity has been satisifed but no completed transaction exists')),
	assert(errorCode('CRP015', 'All operations completed for manufacturing order but manufacturing order not marked complete')),
	assert(errorCode('CRP016', 'Operation exceeds alotted schedule')),
	assert(errorCode('CRP017', 'Resource available interval malformed data')),
	assert(errorCode('CRP018', 'Abnormal termination of schedule')),
	Status = 'complete', !.
	


benchmark3(ScheduledTaskTree):-
	write('In benchmark3\n'),
	compile('wcandtaskdata.yap'),
        getScheduleStartTime(ScheduleStartTime),
        assert(schedule_start_time(ScheduleStartTime)),
   	open('errors.csv', 'append', ErrorStream),
   	%open('errors.csv', 'write', ErrorStream),
	initializeErrorCodes(Status),
	%format(ErrorStream, "Error_ID, MFG_Order, OpNum, WorkCenter, Description~n",[]),
	get_wcdata(WcData),
        createResourceTree(WcData, ResourceTree),
	%outputResourceList(ResourceList)
        get_taskdata(TaskData),
        createUnscheduledTaskList(TaskData, ErrorStream, ResourceTree, UnscheduledTaskList),
	
	%write(UnscheduledTaskList).
	schedule_tasks(UnscheduledTaskList, ErrorStream, ResourceTree, ScheduledTaskTree, ErrorList),
	write_scheduledtasks(ScheduledTaskTree),
        close(ErrorStream).
	%write_scheduling_errors(ErrorList).
	

testResourceListReplacement:-
	write('In testResourceListReplacement:-\n'),
	rb_new(T1),
	rb_new(T2),
	R1 = resource(1, 1, T1),
	R2 = resource(1, 1, T2),
	ScheduledTask = scheduled_task(1, 1, 1, 10, 1),
	replaceResourceAvailableIntervalTreeInResourceList([R1], ScheduledTask, T2, NewResourceList).
	

benchmark2(NumTasks, Duration, ScheduledTasks):-
	createBenchmarkTasks(NumTasks, Duration, UnscheduledTaskList),
	simpleScheduleTasks(UnscheduledTaskList, ScheduledTasks).

benchmark(NumTasks, Duration, ScheduledTasks):- !,
	createBenchmarkTasks(NumTasks, Duration, UnscheduledTaskList),
        rb_new(NewR1AvailableIntervalTree),
	UpperResourceInterval is NumTasks * Duration,
	rb_insert(NewR1AvailableIntervalTree, 1, UpperResourceInterval, R1AvailableIntervalTree),
        R1 = resource('Resource 1', 1, R1AvailableIntervalTree),
        ResourceList = [R1],
	schedule_tasks(UnscheduledTaskList, ResourceList, ScheduledTasks).

testFindClosestIntervalKey(Key):-
	rb_new(AvailableIntervalsTree),
	rb_insert(AvailableIntervalsTree, 100, 120, NewAvailableIntervalsTree1),
	rb_insert(NewAvailableIntervalsTree1, 130, 150, NewAvailableIntervalsTree2),
	rb_insert(NewAvailableIntervalsTree2, 160, 180, NewAvailableIntervalsTree3),
	EST = 151,
	findClosestIntervalKey(EST,NewAvailableIntervalsTree3, IntervalKey, InInterval),
	write('\nInInterval: '),
	write(InInterval),
	write('\nIntervalKey: '),
	write(IntervalKey),
        (InInterval = 0 ->
           Key = IntervalKey
           ;
           Key = EST
        ).
	
	
		

test1(ScheduledTasks):-
	T1 = unscheduled_task('Task 1', 1, 1,8),
	T2 = unscheduled_task('Task 2', 2, 1,8),
	T3 = unscheduled_task('Task 3', 3, 1,5),
	T4 = unscheduled_task('Task 4', 4, 1,8),
	UnscheduledTaskList = [T1,T2,T3,T4],
	%UnscheduledTaskList = [T1, T2],
        rb_new(NewR1AvailableIntervalTree),
	rb_insert(NewR1AvailableIntervalTree, 1,100, R1AvailableIntervalTree),
        R1 = resource('Resource 1', 1, R1AvailableIntervalTree),
        ResourceList = [R1],
	schedule_tasks(UnscheduledTaskList, ResourceList, ScheduledTasks).
	
	
pruneChildNodeList(ChildNodeList, PrunedChildNodeList):-!,
	pruneChildNodeList(ChildNodeList, [], PrunedChildNodeList).
	

pruneChildNodeList([], PrunedChildNodeList, PrunedChildNodeList):-!.

pruneChildNodeList([ChildNode|ChildNodes], TempPrunedChildNodeList, PrunedChildNodeList):-
	(isEmpty(ChildNode) -> 
           write('Child node was empty not adding\n'),
	   %pruneChildNodeList(ChildNodes, TempPrunedChildNodeList, PrunedChildNodeList)
	   NewTempPrunedChildNodeList = TempPrunedChildNodeList
	   ;
           write('Child node was not empty adding\n'),
           append([ChildNode], TempPrunedChildNodeList, NewTempPrunedChildNodeList),	
	   getKeyVal(ChildNode, K, V),
	   write('\nK: '),
	   write(K)
        ),
	   pruneChildNodeList(ChildNodes, NewTempPrunedChildNodeList, PrunedChildNodeList).
	
isEmpty(black([],[],[],[])):-!.

isEmpty(red([],[],[],[])):-!.
	
getKeyVal(black(_,K,V,_), K, V):-!.

getKeyVal(red(_,K,V,_), K, V):-!.

