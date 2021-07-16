% sq.m
% Sums the Fourier Series of a square wave.
% WJD 17.6.02

%%%%%%%%%%%%%%%%%%% define some values %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
close all;                                                                      % close any existing graphics windows
clear all;                                                                      % clear any existing data from memory
fs = 16000;  %fc                                                                % set sampling freq for time series
N = 100;                                                                                % number of points in time series
deltat = 1/fs;                                                          % sampling interval
tmax = deltat*(N-1);                                            % how long are the signals?
t = 0:deltat:tmax;                                              % vector of (sampled) time values
f1 = 250; %fm                                                                   % set fundamental freq of square wave
H = 1;                                                                          % amplitude of square wave

%%%%%%%%%%%%%%%%%%% sum Fourier Series %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
sum = 0;                                                                                % summed Fourier Series
hold on;                                                                                % draw subsequent graphs on the same pair of axes
for n = 1:2:11                                                          % loop for each odd harmonic, from 1 to 11
   b = 4*H/(n*pi);                                              % amplitudes of sine harmonics of F.S.
   harm = b * sin(n*2*pi*f1*t);         % create individual harmonic of F.S.
   plot(t,harm);                                                        % plot first 100 points of each harmonic against time
   sum = sum + harm;                                            % add each harmonic to the sum of the F.S.
end
xlabel('Time (s)');
ylabel('Amplitude');

hold off;                                                                       % subsequent plot commands go on their own axes
figure;                                                                         % new graphics window
plot(t,sum);                                                            % PLot summed F.S.
xlabel('Time (s)');
ylabel('Amplitude');
