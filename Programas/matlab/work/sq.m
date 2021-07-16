% sqfs.m
% Plots a square wave and the sum of its Fourier Series.
% Requires the Signal Processing Toolbox
% WJD 17.6.02

%%%%%%%%%%%%%%%%%%% define some values %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
close all;                                                                      % close any existing graphics windows
clear all;                                                                      % clear any existing data from memory
fs = 16000;                                                                     % set sampling freq for wavwrite
N = 16000;                                                                      % number of points in time series
deltat = 1/fs;                                                          % sampling interval
tmax = deltat*(N-1);                                            % how long are the signals?
t = 0:deltat:tmax;                                              % vector of (sampled) time values

%%%%%%%%%%%%%%%%%%% generate square wave %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
f1 = 250;                                                                       % set freq of square wave
H = 1;                                                                          % amplitude of square wave
signal = square(2*pi*f1*t);                     % generate square wave in the vector 'signal'

%%%%%%%%%%%%%%%%%%% sum Fourier Series %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
sum = 0;                                                                                % summed Fourier Series
growth = 0;                                                                     % initialise a new 'sum'
hold on;                                                                                % draw subsequent graphs on the same pair of axes
for n = 1:2:11                                                          % only calculate odd harmonics, from 1 to 11
   b = 4*H/(n*pi);                                              % amplitudes of sine harmonics of F.S.
   harm = b * sin(n*2*pi*f1*t);         % create individual harmonic of F.S.
   plot(t(1:100),harm(1:100));          % plot first 100 points of each harmonic against time
   sum = sum + harm;                                            % add each harmonic to the sum of the F.S.
   growth = [growth sum];                               % concatonate the sum so far (for aural demo)
end
xlabel('Time (s)');
ylabel('Amplitude');

hold off;                                                                       % subsequent plot commands go on their own axes
figure;                                                                         % new graphics window
plot(t(1:100),signal(1:100),t(1:100),sum(1:100));       % Compare original square wave to its F.S.
xlabel('Time (s)');
ylabel('Amplitude');

%%%%%%%%%%%%%%%%%%% create wav files for demo %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
norm=max(abs(growth));                                  % find max value to allow normalisation to [-1,+1]
wavwrite(growth/norm,fs,'sqfs');                % the growing F.S. of the square wave
% The square wave has to be passed through a low-pass filter before creating
% a wav file, to avoid aliasing. The cut-off frequency of the LPF will be set at
% the Nyquist frequency (half the sampling frequency at which we write the wav file).
% Note that we deal with filtering later in the course! Don't expect to understand this
% bit in week 1!
ts=linspace(-0.5,0.5,N);                                % time values at which to create filter coefficients
fc=fs/2;                                                                                % filter cut-off frequency set to Nyquist freq
h=sinc(ts*fc);                                                          % filter coefficients (simple LPF)
lpfsig=conv(signal,h);                                  % use convolution to filter the signal
lpfsig(1:N-1)=[];                                                       % truncate initial transient from filtered signal
norm=max(abs(lpfsig));                                  % find max value to allow normalisation to [-1,+1]
wavwrite(lpfsig/norm,fs,'square');      % write the filtered square wave to file
