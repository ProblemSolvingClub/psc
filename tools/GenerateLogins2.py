# Teams generator for UofC Programming Contest Control Centre
# -----------------------------------------------------------
#
# This Python 3 script takes an Eventbrite CSV file and generates:
#
# teams.txt files for judge:
#   - teams0.txt: All teams, for practice contest usually
#   - teams1.txt: Division 1 teams only
#   - teams2.txt: Division 2 teams only
#
# labels.csv: Username/password labels to print out (for onsite contestants only)
# emails.csv: Pre-formatted emails to send out (for offsite contestants only)
#
# If you want to use this script, carefully customize it to your needs before
# running it. You need to decide what makes a contestant official, onsite, etc.
import csv, random

# Seed random to make password generation deterministic
# This ensures that the same passwords are generated for each run
# *** CHANGE THE SEED BEFORE USING THIS SCRIPT
# *** THE OLD SEED HAS PROBABLY BEEN USED TO GENERATE PASSWORDS FOR A PREVIOUS CONTEST
random.seed(1497152124)

pwChars = "qwetyupasdfghjkzxcvb23456789";
def genPassword():
	return ''.join(random.choice(pwChars) for i in range(8))

# We'll start div1 at team100 and div2 at team300
# Be sure not to overlap numbers, because the practice contest usually has all teams
teamNums = {1: 100, 2: 300}
teams = {}

# Add blank accounts for all divisions
for i in range(1, 6):
	for division in teamNums:
		teamNums[division] += 1
		teamName = 'Div %d Official Account %d' % (division, i)
		teams[teamName] = {
			'username': 'team%d' % teamNums[division],
			'password': genPassword(),
			'official': True,
			'onsite': True,
			'divisions': [0, division],
			'members': set()
		}

# Add UBC accounts
for i in range(1, 6):
	teamName = "UBC %d" % i
	teams[teamName] = {
		'username': 'ubc%d' % i,
		'password': 'ubc%d' % i,
		'official': False,
		'onsite': False,
		'divisions': [0, 1, 2],
		'members': set()
	}

csvfile = open('ccpc2016.csv', newline='')
csvreader = csv.reader(csvfile)
for row in csvreader:
	firstName = row[2]
	lastName = row[3]
	email = row[4]
	division = 1 if row[6].find('1') != -1 else 2
	teamName = row[7].replace(';', '')
	onsite = row[10].startswith('Yes')
	official = row[11].startswith('Yes')

	# Some idiot decided to put 3 unicode spaces as their team name
	if teamName == '\u200b\u200b\u200b':
		teamName = "Too Cool to Have a Name"
	
	if teamName not in teams:
		teamNums[division] += 1
		teams[teamName] = {
			'username': 'team%d' % teamNums[division],
			'password': genPassword(),
			'official': official,
			'onsite': onsite,
			'divisions': [0, division],
			'members': set(),
			'emails': set()
		}
	teams[teamName]['members'].add('%s %s' % (firstName, lastName))
	teams[teamName]['emails'].add(email)
files = [open('teams%d.txt' % i, 'w') for i in range(3)]
labelFile = open('labels.csv', 'w', newline='')
labelWriter = csv.writer(labelFile)
emailFile = open('emails.csv', 'w', newline='')
emailWriter = csv.writer(emailFile)
for teamName in sorted(teams.keys(), key=lambda x: teams[x]['username']):
	t = teams[teamName]
	division = t['divisions'][1]
	line = "%s;%s;%s;%s;%s\n" % (
		t['username'],
		t['password'],
		'1' if t['official'] else '-1',
		teamName,
		', '.join(sorted(t['members'])))
	for d in t['divisions']:
		files[d].write(line)
	if t['onsite']:
		labelWriter.writerow([
			'Division %d %s' % (division, 'Official' if t['official'] else 'Unofficial'),
			teamName,
			'Username: %s' % t['username'],
			'Password: %s' % t['password']
		])
	if not t['official'] and 'emails' in t:
		emailWriter.writerow([
			'; '.join(sorted(t['emails'])),
			'Your login information for CCPC 2016 Division %d\n\nTeam: %s\nUsername: %s\nPassword: %s\nPractice: http://psc.cpsc.ucalgary.ca/ccpc/2016/practice/\nContest: http://psc.cpsc.ucalgary.ca/ccpc/2016/contest/div%d/\nContest time: 1pm-5pm Mountain Time' %
			(division, teamName, t['username'], t['password'], division)
		])

[x.close() for x in files]
emailFile.close()
labelFile.close()
csvfile.close()
